package exeptions;

public class BrowserNotSupported extends RuntimeException {

    public BrowserNotSupported(String browserName){
        super(String.format("Browser %s is not supported", browserName));
    }
}
