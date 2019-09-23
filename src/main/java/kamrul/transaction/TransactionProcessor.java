package kamrul.transaction;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

import kamrul.IProcess;
import kamrul.IResult;
import kamrul.account.Balance;
import kamrul.transaction.Transaction;
import kamrul.transaction.TransactionType;

public class TransactionProcessor implements IProcess {
    private List<Transaction> transactions;
    private Map<String, List<Transaction>> transactionsMapForAccount;
    private Map<String, Transaction> transactionsMapForReversal;

    public TransactionProcessor(List<Transaction> transactions) {
        this.transactions = transactions;
        this.transactionsMapForAccount = new HashMap<String, List<Transaction>>();
        this.transactionsMapForReversal = new HashMap<String,Transaction>();
        this.preProcessTransactions(); 
    }

    public IResult calculateRelativeAccountBalance(String accountId, String fromDateStr, String toDateStr) {
        List<Transaction> transactionsToConsider = this.transactionsMapForAccount.get(accountId);
        Date fromDate = new Date(), toDate = new Date();
        try {
            fromDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(fromDateStr);
            toDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(toDateStr);
    
        } catch (Exception e) {
            e.printStackTrace();
        }

        Float totalAmount = 0.0f;
        Integer totalTransactions = 0;

        for(Transaction transaction : transactionsToConsider) {
            if (
            !this.transactionsMapForReversal.containsKey(transaction.getTransactionId()) &&    
            transaction.getCreateAt().compareTo(fromDate) >= 0 && 
            transaction.getCreateAt().compareTo(toDate) <= 0) {
                totalTransactions += 1;
                totalAmount += getAmountForAccount(transaction, accountId);
            }
        }

        return new Balance(totalTransactions, totalAmount);
    }

    /**
     * This will optimize the lookup time for a particular accountId.
     * It considers both from and to accounts
     */
    private void preProcessTransactions() {
        transactions.forEach(transaction -> {
            String fromAccount = transaction.getFromAccountId();
            String toAccount = transaction.getToAccountId();
            if (fromAccount.equals(toAccount)) {
                return;
            }
            if(transaction.getTransactionType() == TransactionType.REVERSAL) {
                this.transactionsMapForReversal.put(transaction.getRelatedTransaction(), transaction);
                return;
            }
            if (!transactionsMapForAccount.containsKey(fromAccount)) {
                transactionsMapForAccount.put(fromAccount, new ArrayList());
            } 
            transactionsMapForAccount.get(fromAccount).add(transaction);

            if (!transactionsMapForAccount.containsKey(toAccount)) {
                transactionsMapForAccount.put(toAccount, new ArrayList());
            } 
            transactionsMapForAccount.get(toAccount).add(transaction);


        });
    }

    private Float getAmountForAccount(Transaction transaction, String accountId) {
        if (transaction.getFromAccountId().equals(accountId)) {
            return transaction.getAmount() * (-1.0f);
        }
        return transaction.getAmount();
    }
}