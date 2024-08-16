package kadriozcan.marketplaceapp.controller;

import kadriozcan.marketplaceapp.common.Constants;
import kadriozcan.marketplaceapp.dto.BaseResponse;
import kadriozcan.marketplaceapp.dto.product.ProductDto;
import kadriozcan.marketplaceapp.dto.seller.SellerDto;
import kadriozcan.marketplaceapp.service.BlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/blacklists")
public class BlacklistController {
    private final BlacklistService blacklistService;

    @GetMapping
    public BaseResponse<List<SellerDto>> getBlacklist(@RequestParam String userId) {
        List<SellerDto> blacklistSellerDtos = blacklistService.getBlacklistSellers(userId);
        return new BaseResponse<>(blacklistSellerDtos);
    }

    @PostMapping
    public BaseResponse<String> addBlacklist(@RequestParam String userId, @RequestParam String sellerName) {
        try {
            blacklistService.addSellerToBlacklist(userId, sellerName);
            return new BaseResponse<>(Constants.ResponseCodes.BLACKLIST_IS_ADDED,
                    String.format("Seller: %s added to blacklist", sellerName));
        } catch (Exception e) {
            return new BaseResponse<>(Constants.ResponseCodes.SELLER_IS_ALREADY_IN_BLACKLIST,
                    String.format("Seller: %s is already in blacklist", sellerName));
        }
    }

    @DeleteMapping
    public BaseResponse<String> delete(@RequestParam String userId, @RequestParam String sellerId) {
        blacklistService.removeSellerFromBlacklist(userId, sellerId);
        return new BaseResponse<>(Constants.ResponseCodes.SELLER_IS_REMOVED_FROM_BLACKLIST,
                String.format("Seller removed successfully. Seller ID: %s User ID: %s", sellerId, userId));
    }
}
