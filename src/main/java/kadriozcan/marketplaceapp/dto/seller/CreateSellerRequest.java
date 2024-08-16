package kadriozcan.marketplaceapp.dto.seller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateSellerRequest {
    @NotBlank
    @Size(min = 2, max = 30)
    private String name;
}
