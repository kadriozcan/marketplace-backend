package kadriozcan.marketplaceapp.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import kadriozcan.marketplaceapp.model.Seller;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
public class CreateProductRequest {

    @NotBlank
    @Size(min = 2, max = 30)
    private String name;

    private String description;

    @NotBlank
    private BigDecimal price;

    @NotBlank
    private String categoryName;

    @NotBlank
    private String sellerName;
}
