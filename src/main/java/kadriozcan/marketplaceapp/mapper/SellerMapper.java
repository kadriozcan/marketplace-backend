package kadriozcan.marketplaceapp.mapper;

import kadriozcan.marketplaceapp.dto.seller.SellerDto;
import kadriozcan.marketplaceapp.model.Seller;
import org.springframework.stereotype.Component;

@Component
public class SellerMapper {
    public SellerDto toDto(Seller seller) {
        SellerDto sellerDto = SellerDto.builder()
                .id(seller.getId().toString())
                .name(seller.getName())
                .build();
        return sellerDto;
    }
}
