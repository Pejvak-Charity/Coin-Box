package charity.pejvak.coinbox.exception;

public class NoSuchCoinBoxException extends RuntimeException{
    public NoSuchCoinBoxException(String s) {
        super(s);
    }

    public NoSuchCoinBoxException() {
        super();
    }
}
