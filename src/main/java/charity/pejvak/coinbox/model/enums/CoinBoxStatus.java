package charity.pejvak.coinbox.model.enums;

public enum CoinBoxStatus {
    ACTIVE(1);
    private final int code;

    CoinBoxStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
