package charity.pejvak.coinbox.model.enums;

public enum CoinBoxUserRequestStatus {
    SUBMITTED("Submitted"),
    COORDINATED("Coordinated")
    ,CANCELLED("Cancelled"),
    DONE("Done");
    private final String text;
    CoinBoxUserRequestStatus(String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
