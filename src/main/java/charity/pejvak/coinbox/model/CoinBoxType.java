package charity.pejvak.coinbox.model;

public enum CoinBoxType {
    STORE("store"),HOMEMADE("homemade"),STANDING("standing");
    private final String name;

    CoinBoxType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
