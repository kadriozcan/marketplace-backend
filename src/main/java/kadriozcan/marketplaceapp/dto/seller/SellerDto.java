package kadriozcan.marketplaceapp.dto.seller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
public class SellerDto {
    private String id;
    private String name;
}
