package kadriozcan.marketplaceapp.exception;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String userId) {
        super(String.format("User not found: %s", userId));
    }
}
