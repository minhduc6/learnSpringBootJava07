package vn.techmaster.bank.model;

public class AccountInfo {
    String accountId;
    String bank_name;
    Long amount;

    public AccountInfo() {
    }

    public AccountInfo(String accountId, String bank_name, Long amount) {
        this.accountId = accountId;
        this.bank_name = bank_name;
        this.amount = amount;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
