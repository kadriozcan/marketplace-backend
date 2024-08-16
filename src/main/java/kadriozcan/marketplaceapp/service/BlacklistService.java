package kadriozcan.marketplaceapp.service;

import kadriozcan.marketplaceapp.dto.seller.SellerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlacklistService {
    void addSellerToBlacklist(String userId, String sellerName) throws Exception;

    List<SellerDto> getBlacklistSellers(String userId);

    void removeSellerFromBlacklist(String userId, String sellerId);
}
