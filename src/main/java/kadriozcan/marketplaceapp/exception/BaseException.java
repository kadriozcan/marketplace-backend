package kadriozcan.marketplaceapp.exception;

public class BaseException extends RuntimeException {
    protected BaseException(String message) {
        super(message);
    }

    protected BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    protected BaseException() {
    }
}
