package charity.pejvak.coinbox.model.enums;

public enum CoinBoxRequestStatus {
    SUBMITTED("Submitted"),COORDINATED("Coordinated"),CANCELLED("Cancelled"),
    DONE("Done");
    private final String text;
    CoinBoxRequestStatus(String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
