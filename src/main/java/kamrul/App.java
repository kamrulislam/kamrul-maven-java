package kamrul;


import kamrul.ICsvReader;
import kamrul.IProcess;
import kamrul.IResult;
import kamrul.account.Balance;
import kamrul.file.FileReader;
import kamrul.transaction.Transaction;
import kamrul.transaction.TransactionProcessor;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {

        //input processing
        ICsvReader csvReader = new FileReader("./input.csv");
        List<String> inputFileData = csvReader.getDataFromCsv();
        List<Transaction> transactions = new ArrayList<Transaction>();
        inputFileData.forEach((strData) -> transactions.add((new Transaction()).fromStringCsv(strData)));


        //process
        IProcess processor = new TransactionProcessor(transactions);

        IResult result = processor.calculateRelativeAccountBalance(
                "ACC334455",
                "20/10/2018 12:00:00",
                "20/10/2018 19:00:00"
        );

        System.out.println("Relative balance for the period is: " + result.getAmount());
        System.out.println("Number of transactions included is: " + result.getNumberOfTransactions());
    }
}
