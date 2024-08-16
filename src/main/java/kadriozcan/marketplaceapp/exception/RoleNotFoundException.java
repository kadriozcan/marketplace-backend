package kadriozcan.marketplaceapp.exception;

public class RoleNotFoundException extends BaseException {
    public RoleNotFoundException(String roleName) {
        super(String.format("Role not found: %s", roleName));
    }
}
