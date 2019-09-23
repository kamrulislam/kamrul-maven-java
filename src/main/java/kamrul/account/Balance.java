package kamrul.account;

import kamrul.IResult;

public class Balance implements IResult {
    private Integer numberOfTransactions;
    private Float amount;

    public Balance(Integer numberOfTransactions, Float amount) {
        this.numberOfTransactions = numberOfTransactions;
        this.amount = amount;
    }

    public Float getAmount() {
        return this.amount;
    }

    public Integer getNumberOfTransactions() {
        return this.numberOfTransactions;
    }
}