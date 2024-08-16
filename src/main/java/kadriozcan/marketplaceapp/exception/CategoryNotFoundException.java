package kadriozcan.marketplaceapp.exception;

public class CategoryNotFoundException extends BaseException {
    public CategoryNotFoundException(String categoryName) {
        super(String.format("Category %s not found", categoryName));
    }
}
