package kadriozcan.marketplaceapp.controller;

import kadriozcan.marketplaceapp.common.Constants;
import kadriozcan.marketplaceapp.dto.BaseResponse;
import kadriozcan.marketplaceapp.dto.product.ProductDto;
import kadriozcan.marketplaceapp.dto.product.CreateProductRequest;
import kadriozcan.marketplaceapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public BaseResponse<List<ProductDto>> getAllProducts(@RequestParam(required = false) List<String> categories,
                                                         @RequestParam(required = false) String searchTerm) {
        return new BaseResponse<>(productService.listProducts(categories, searchTerm));
    }

    @GetMapping("/{productId}")
    public BaseResponse<ProductDto> getProductById(@PathVariable String productId) {
        return new BaseResponse<>(productService.getById(productId));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public BaseResponse<ProductDto> createProduct(@RequestBody CreateProductRequest productRequest) {
        return new BaseResponse<>(productService.create(productRequest));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{productId}")
    public BaseResponse<ProductDto> deleteProduct(@PathVariable String productId) {
        productService.delete(productId);
        return new BaseResponse<>(Constants.ResponseCodes.SUCCESS, "Product deleted successfully");
    }

}
