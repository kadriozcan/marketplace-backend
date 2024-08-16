package kadriozcan.marketplaceapp.common;


import jakarta.transaction.Transactional;
import kadriozcan.marketplaceapp.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class DataLoader implements ApplicationRunner {
    private final UserService userService;
    private final RoleService roleService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final SellerService sellerService;

    @Transactional
    @Override
    public void run(ApplicationArguments args) {
        roleService.checkAndCreateRoles(List.of(Constants.Roles.ADMIN, Constants.Roles.USER));

        if (userService.listUsers().size() <= 3) {
            userService.generateSampleUsers();
        }
        userService.checkAndCreateAdminUser();

        if (categoryService.listCategories().isEmpty()) {
            categoryService.generateSampleCategories();
        }

        if (sellerService.listSellers().isEmpty()) {
            sellerService.generateSampleSellers();
        }

        if (productService.listProducts(List.of(), "").isEmpty()) {
            productService.generateSampleProducts();
        }


    }
}
