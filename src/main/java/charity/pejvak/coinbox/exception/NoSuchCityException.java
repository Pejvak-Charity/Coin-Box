package charity.pejvak.coinbox.exception;

public class NoSuchCityException extends RuntimeException{
    public NoSuchCityException(String s) {
        super(s);
    }

    public NoSuchCityException() {
        super();
    }
}
