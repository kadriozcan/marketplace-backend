package kadriozcan.marketplaceapp.service.impl;

import kadriozcan.marketplaceapp.dto.product.ProductDto;
import kadriozcan.marketplaceapp.exception.ProductNotFoundException;
import kadriozcan.marketplaceapp.exception.UserNotFoundException;
import kadriozcan.marketplaceapp.mapper.ProductMapper;
import kadriozcan.marketplaceapp.model.Favorite;
import kadriozcan.marketplaceapp.model.Product;
import kadriozcan.marketplaceapp.model.User;
import kadriozcan.marketplaceapp.repository.FavoriteRepository;
import kadriozcan.marketplaceapp.repository.ProductRepository;
import kadriozcan.marketplaceapp.repository.UserRepository;
import kadriozcan.marketplaceapp.service.FavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FavoriteServiceImpl implements FavoriteService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final FavoriteRepository favoriteRepository;
    private final ProductMapper productMapper;

    @Override
    public void addProductToFavorites(String userId, String productId) throws Exception {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new UserNotFoundException(userId));
        Product product = productRepository.findById(UUID.fromString(productId))
                .orElseThrow(() -> new ProductNotFoundException(productId));

        List<Favorite> existingFavorites = user.getFavorites();


        for (Favorite favorite : existingFavorites) {
            if (favorite.getProduct().getId().equals(product.getId())) {
                throw new Exception("Already in favorites");
            }
        }
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);
        user.addFavorite(favorite);
        userRepository.save(user);


    }

    @Override
    public void removeProductFromFavorites(String userId, String productId) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new UserNotFoundException(userId));
        user.getFavorites().removeIf(favorite -> favorite.getProduct().getId().equals(UUID.fromString(productId)));
        userRepository.save(user);
    }

    @Override
    public List<ProductDto> getFavoritesByUserId(String userId) {
        List<Favorite> favorites = favoriteRepository.findAllByUserId(UUID.fromString(userId));
        List<ProductDto> productDtos = new ArrayList<>();
        for (Favorite favorite : favorites) {
            UUID favoriteProductId = favorite.getProduct().getId();
            Product product = productRepository.findById(favoriteProductId)
                    .orElseThrow(() -> new ProductNotFoundException(favoriteProductId.toString()));
            productDtos.add(productMapper.toDto(product));
        }

        return productDtos;
    }
}
