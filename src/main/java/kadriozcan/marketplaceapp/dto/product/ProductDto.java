package kadriozcan.marketplaceapp.dto.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
public class ProductDto {
    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private String categoryName;

    private String sellerName;
}
