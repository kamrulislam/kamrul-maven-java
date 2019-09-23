package kamrul.transaction;

import java.text.SimpleDateFormat;
import java.util.Date;

import kamrul.transaction.TransactionType;

public class Transaction {
    private String transactionId;
    private String fromAccountId;
    private String toAccountId;
    private Date createAt;
    private float amount;
    private TransactionType transactionType;
    private String relatedTransaction;

    private SimpleDateFormat dateFormatter;

    public Transaction() {
        this.dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    public Transaction fromStringCsv(String csvData) {
        try {
            String[] strDataArr = csvData.split(",", -2);
            this.transactionId = strDataArr[0].trim();
            this.fromAccountId = strDataArr[1].trim();
            this.toAccountId = strDataArr[2].trim();
            this.createAt = this.dateFormatter.parse(strDataArr[3].trim());
            this.amount = Float.parseFloat(strDataArr[4].trim());
            this.transactionType = TransactionType.valueOf(strDataArr[5].trim());
            if (this.transactionType == TransactionType.REVERSAL) {
                this.relatedTransaction = strDataArr[6].trim();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }


    public String getFromAccountId() {
        return fromAccountId;
    }


    public String getToAccountId() {
        return toAccountId;
    }


    public Date getCreateAt() {
        return createAt;
    }


    public float getAmount() {
        return amount;
    }


    public TransactionType getTransactionType() {
        return transactionType;
    }


    public String getRelatedTransaction() {
        return relatedTransaction;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Transaction(transactionId:" + transactionId +
        ", fromAccountId:" + fromAccountId +
        ", toAccountId:" + toAccountId + 
        ", amount: " + amount + 
        ", createAt: " + createAt +
        ", transactionType: " + transactionType + 
        ", relatedTransaction: " + (relatedTransaction != null ? relatedTransaction : "") + ")" ;
    }

}