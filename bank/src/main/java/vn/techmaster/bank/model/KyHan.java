package vn.techmaster.bank.model;

public enum KyHan {
    ONE_MONTH("Một Tháng"),
    THREE_MONTH("Ba Tháng"),
    SIX_MONTH("Sáu Tháng"),
    TWELVE_MONTH("Mười Hai Tháng");

    public final String label;
    private KyHan(String label) {
        this.label = label;
    }
}
