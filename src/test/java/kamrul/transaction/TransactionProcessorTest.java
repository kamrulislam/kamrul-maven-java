package kamrul.transaction;


import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;
import java.util.ArrayList;

import kamrul.IResult;


public class TransactionProcessorTest
{
    @Test
    public void shouldGiveCorrectTransactionCountAndAmount()
    {
        List<Transaction> transactions = new ArrayList<Transaction>();
        Transaction transaction = new Transaction();
        transaction.fromStringCsv("TX10001, ACC334455, ACC778899, 20/10/2018 12:47:55, 25.00, PAYMENT");
        transactions.add(transaction);
        transaction = new Transaction();
        transaction.fromStringCsv("TX10004, ACC334455, ACC998877, 20/10/2018 19:45:00, 10.50, REVERSAL, TX10002");
        transactions.add(transaction);

        TransactionProcessor processor = new TransactionProcessor(transactions);
        IResult result = processor.calculateRelativeAccountBalance("ACC334455", "20/10/2018 12:00:00", "20/10/2018 20:00:00");
        Integer count = result.getNumberOfTransactions();
        Integer expected = new Integer(1);
        assertEquals(expected, count);


        Float amount = result.getAmount();
        Float expectedAmount = new Float(-25.00);
        assertEquals(expectedAmount, amount);
    }


    @Test
    public void shouldNotConsiderReversal()
    {
        List<Transaction> transactions = new ArrayList<Transaction>();
        Transaction transaction = new Transaction();
        transaction.fromStringCsv("TX10001, ACC334455, ACC998877, 20/10/2018 12:47:55, 25.00, PAYMENT");
        transactions.add(transaction);
        transaction = new Transaction();
        transaction.fromStringCsv("TX10004, ACC334455, ACC998877, 20/10/2018 19:45:00, 25.00, REVERSAL, TX10001");
        transactions.add(transaction);

        TransactionProcessor processor = new TransactionProcessor(transactions);
        IResult result = processor.calculateRelativeAccountBalance("ACC334455", "20/10/2018 12:00:00", "20/10/2018 20:00:00");
        Integer count = result.getNumberOfTransactions();
        Integer expected = new Integer(0);
        assertEquals(expected, count);


        Float amount = result.getAmount();
        Float expectedAmount = new Float(0.0);
        assertEquals(expectedAmount, amount);
    }

}
