package kadriozcan.marketplaceapp.service;


import kadriozcan.marketplaceapp.dto.product.ProductDto;

import java.util.List;

public interface FavoriteService {

    void addProductToFavorites(String userId, String productId) throws Exception;

    void removeProductFromFavorites(String userId, String productId);

    List<ProductDto> getFavoritesByUserId(String userId);
}
