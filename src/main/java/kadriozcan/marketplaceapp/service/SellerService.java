package kadriozcan.marketplaceapp.service;

import kadriozcan.marketplaceapp.dto.seller.CreateSellerRequest;
import kadriozcan.marketplaceapp.dto.seller.SellerDto;

import java.util.List;

public interface SellerService {
    List<SellerDto> listSellers();

    SellerDto create(CreateSellerRequest createRequest);

    void generateSampleSellers();
}
