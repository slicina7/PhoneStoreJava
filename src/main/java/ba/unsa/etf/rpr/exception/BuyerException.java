package ba.unsa.etf.rpr.exception;
/**
 * Exceptios for DAO classes
 *
 * @author Selma Ličina
 */
public class BuyerException extends Exception{
    public BuyerException(String message){
        super(message);
    }

    public BuyerException(String message, Exception reason) {
        super(message, reason);
    }
}
