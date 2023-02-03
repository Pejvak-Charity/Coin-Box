package charity.pejvak.coinbox.model.enums;

public enum CoinBoxUserRequestType {


    //TODO complete name and messages
    GET_COINBOX("",""),
    COINBOX_COUNT("",""),
    COINBOX_RETURN("","")
    ;

    private final String name;
    private final String logMessage;

    CoinBoxUserRequestType(String name, String logMessage) {
        this.name = name;
        this.logMessage = logMessage;
    }

    public String getName() {
        return name;
    }

    public String getLogMessage() {
        return logMessage;
    }
}
