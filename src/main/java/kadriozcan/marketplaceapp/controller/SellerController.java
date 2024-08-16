package kadriozcan.marketplaceapp.controller;

import kadriozcan.marketplaceapp.dto.BaseResponse;
import kadriozcan.marketplaceapp.dto.seller.CreateSellerRequest;
import kadriozcan.marketplaceapp.dto.seller.SellerDto;
import kadriozcan.marketplaceapp.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;

    @GetMapping
    public BaseResponse<List<SellerDto>> getAll() {
        return new BaseResponse<>(sellerService.listSellers());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public BaseResponse<SellerDto> create(@RequestBody CreateSellerRequest sellerRequest) {
        return new BaseResponse<>(sellerService.create(sellerRequest));
    }
}
