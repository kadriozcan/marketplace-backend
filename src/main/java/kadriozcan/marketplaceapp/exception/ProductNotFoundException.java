package kadriozcan.marketplaceapp.exception;

public class ProductNotFoundException extends BaseException {
    public ProductNotFoundException(String id) {
        super(String.format("Product with id %s not found", id));
    }
}
