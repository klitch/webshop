package exception;

/**
 * Created by Vitalii_Bandura on 4/27/2015.
 */
public class DAOException extends RuntimeException {
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
