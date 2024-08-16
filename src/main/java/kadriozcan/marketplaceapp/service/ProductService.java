package kadriozcan.marketplaceapp.service;

import kadriozcan.marketplaceapp.dto.product.ProductDto;
import kadriozcan.marketplaceapp.dto.product.CreateProductRequest;

import java.util.List;

public interface ProductService {
    List<ProductDto> listProducts(List<String> categories, String searchTerm);

    ProductDto getById(String id);

    ProductDto create(CreateProductRequest productRequest);

    void generateSampleProducts();

    void delete(String productId);
}
