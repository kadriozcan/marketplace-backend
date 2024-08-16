package kadriozcan.marketplaceapp.service.impl;

import kadriozcan.marketplaceapp.dto.product.CreateProductRequest;
import kadriozcan.marketplaceapp.dto.product.ProductDto;
import kadriozcan.marketplaceapp.exception.CategoryNotFoundException;
import kadriozcan.marketplaceapp.exception.ProductNotFoundException;
import kadriozcan.marketplaceapp.exception.SellerNotFoundException;
import kadriozcan.marketplaceapp.mapper.ProductMapper;
import kadriozcan.marketplaceapp.model.Blacklist;
import kadriozcan.marketplaceapp.model.Category;
import kadriozcan.marketplaceapp.model.Product;
import kadriozcan.marketplaceapp.model.Seller;
import kadriozcan.marketplaceapp.repository.BlacklistRepository;
import kadriozcan.marketplaceapp.repository.CategoryRepository;
import kadriozcan.marketplaceapp.repository.ProductRepository;
import kadriozcan.marketplaceapp.repository.SellerRepository;
import kadriozcan.marketplaceapp.security.UserAuthDetails;
import kadriozcan.marketplaceapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SellerRepository sellerRepository;
    private final BlacklistRepository blacklistRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> listProducts(List<String> categories, String searchTerm) {
        List<Product> all = productRepository.findAll();

        if (categories != null && !categories.isEmpty()) {
            all = all.stream().filter(product -> categories.contains(product.getCategory().getName())).toList();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserAuthDetails) {
            UserAuthDetails userAuthDetails = (UserAuthDetails) authentication.getPrincipal();
            UUID userId = userAuthDetails.getUser().getId();
            List<Blacklist> blacklists = blacklistRepository.findAllByUserId(userId);
            List<String> blacklistSellerIds = new ArrayList<>();
            for (Blacklist blacklist : blacklists) {
                blacklistSellerIds.add(blacklist.getSeller().getId().toString());
            }
            all = all.stream().filter(product -> !blacklistSellerIds.contains(product.getSeller().getId().toString())).toList();

        }

        if (searchTerm != null && !searchTerm.isEmpty()) {
            String searchTermLowerCase = searchTerm.toLowerCase();
            all = all.stream().filter(product -> product.getName().toLowerCase().contains(searchTermLowerCase)).toList();
        }
        return all.stream().map(productMapper::toDto).toList();
    }

    @Override
    public ProductDto getById(String id) {
        return productMapper.toDto(productRepository.findById(UUID.fromString(id)).
                orElseThrow(() -> new ProductNotFoundException(id)));
    }

    @Override
    public ProductDto create(CreateProductRequest productRequest) {
        Category category = categoryRepository.findByName(productRequest.getCategoryName()).
                orElseThrow(() -> new CategoryNotFoundException(productRequest.getCategoryName()));
        Seller seller = sellerRepository.findByNameEqualsIgnoreCase(productRequest.getSellerName())
                .orElseThrow(() -> new SellerNotFoundException(productRequest.getSellerName()));
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setCategory(category);
        product.setPrice(productRequest.getPrice());
        product.setDescription(productRequest.getDescription());
        product.setSeller(seller);

        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public void generateSampleProducts() {
        Product product = new Product();
        product.setName("Asus Laptop");
        product.setDescription("i5 13th - nvidia rtx 3060");
        product.setPrice(BigDecimal.valueOf(15599.99));
        product.setCategory(categoryRepository.findByName("Electronics")
                .orElseThrow(() -> new CategoryNotFoundException("Electronics")));
        product.setSeller(sellerRepository.findByNameEqualsIgnoreCase("MediaMarkt")
                .orElseThrow(() -> new SellerNotFoundException("MediaMarkt")));

        productRepository.save(product);
    }

    @Override
    public void delete(String productId) {
        productRepository.deleteById(UUID.fromString(productId));

    }
}
