package kamrul;

import java.util.List;

public interface IProcess {
    public IResult calculateRelativeAccountBalance(String accountId, String fromDateStr, String toDateStr);
}