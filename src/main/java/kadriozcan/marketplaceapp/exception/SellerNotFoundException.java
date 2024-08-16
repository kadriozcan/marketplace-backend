package kadriozcan.marketplaceapp.exception;

public class SellerNotFoundException extends BaseException{
    public SellerNotFoundException(String sellerId) {
        super(String.format("Seller with id %s not found", sellerId));
    }
}
