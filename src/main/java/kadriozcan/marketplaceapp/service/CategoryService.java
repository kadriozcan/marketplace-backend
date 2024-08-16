package kadriozcan.marketplaceapp.service;

import kadriozcan.marketplaceapp.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> listCategories();

    Category create(Category category);

    void generateSampleCategories();
}
