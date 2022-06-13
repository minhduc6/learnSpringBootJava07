package vn.techmaster.bank.model;

public enum StatusSaving {
    TAT_TOAN("Tất Toán"),
    DANG_GUI("Đang gửi"),
    HOAN_THANH("Hoàn Thành");

    public final String label;
    private StatusSaving(String label) {
        this.label = label;
    }
}
