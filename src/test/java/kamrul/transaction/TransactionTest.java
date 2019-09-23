package kamrul.transaction;


import static org.junit.Assert.*;

import org.junit.Test;

public class TransactionTest
{
    @Test
    public void shouldGenerateTransactionWithPaymentType()
    {

        Transaction transaction = new Transaction();
        transaction.fromStringCsv("TX10001, ACC334455, ACC778899, 20/10/2018 12:47:55, 25.00, PAYMENT");

        assertEquals( TransactionType.PAYMENT, transaction.getTransactionType() );
    }

    @Test
    public void shouldGenerateTransactionWithReversalType()
    {

        Transaction transaction = new Transaction();
        transaction.fromStringCsv("TX10004, ACC334455, ACC998877, 20/10/2018 19:45:00, 10.50, REVERSAL, TX10002");

        assertEquals( TransactionType.REVERSAL, transaction.getTransactionType() );
    }

    @Test
    public void shouldHaveRelatedAccountOnReversalType()
    {

        Transaction transaction = new Transaction();
        transaction.fromStringCsv("TX10004, ACC334455, ACC998877, 20/10/2018 19:45:00, 10.50, REVERSAL, TX10002");

        assertNotNull(transaction.getRelatedTransaction() );
    }

}
