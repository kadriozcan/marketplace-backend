package kadriozcan.marketplaceapp.mapper;

import kadriozcan.marketplaceapp.dto.product.ProductDto;
import kadriozcan.marketplaceapp.model.Category;
import kadriozcan.marketplaceapp.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toModel(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(category);
        return product;
    }

    public ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId().toString())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .categoryName(product.getCategory().getName())
                .sellerName(product.getSeller().getName())
                .build();
    }
}
