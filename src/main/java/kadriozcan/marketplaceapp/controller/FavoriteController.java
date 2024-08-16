package kadriozcan.marketplaceapp.controller;

import kadriozcan.marketplaceapp.common.Constants;
import kadriozcan.marketplaceapp.dto.BaseResponse;
import kadriozcan.marketplaceapp.dto.product.ProductDto;
import kadriozcan.marketplaceapp.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;

    @PostMapping
    public BaseResponse<String> save(@RequestParam String userId, @RequestParam String productId) {
        try {
            favoriteService.addProductToFavorites(userId, productId);
            return new BaseResponse<>(Constants.ResponseCodes.FAVORITE_IS_ADDED,
                    String.format("Favorite added successfully. Product ID: %s User ID: %s", productId, userId));
        } catch (Exception e) {
            return new BaseResponse<>(Constants.ResponseCodes.FAVORITE_ALREADY_ADDED,
                    String.format("Favorite was already added. Product ID: %s User ID: %s", productId, userId));
        }

    }

    @DeleteMapping
    public BaseResponse<String> delete(@RequestParam String userId, @RequestParam String productId) {
        favoriteService.removeProductFromFavorites(userId, productId);
        return new BaseResponse<>(Constants.ResponseCodes.FAVORITE_IS_REMOVED,
                String.format("Favorite removed successfully. Product ID: %s User ID: %s", productId, userId));
    }

    @GetMapping
    public BaseResponse<List<ProductDto>> getFavorites(@RequestParam String userId) {
        List<ProductDto> favoriteProductsByUserId = favoriteService.getFavoritesByUserId(userId);
        return new BaseResponse<>(favoriteProductsByUserId);
    }

}
