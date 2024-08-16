package kadriozcan.marketplaceapp.service.impl;

import kadriozcan.marketplaceapp.model.Category;
import kadriozcan.marketplaceapp.repository.CategoryRepository;
import kadriozcan.marketplaceapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void generateSampleCategories() {
        String[] categories = {"Electronics", "Clothing", "Furniture", "Sports"};
        for (String category : categories) {
            Category newCategory = new Category();
            newCategory.setName(category);
            categoryRepository.save(newCategory);
        }
    }
}
