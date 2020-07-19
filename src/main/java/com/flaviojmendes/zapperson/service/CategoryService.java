package com.flaviojmendes.zapperson.service;

import com.flaviojmendes.zapperson.model.Category;
import com.flaviojmendes.zapperson.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    public Iterable<Category> getCategories(String companyId) {
        Iterable<Category> categories = categoryRepository.findAllByCompanyId(companyId);
        categories.forEach(category -> {
            category.setProducts(this.productService.getProducts(category.getId()));
        });
        return categories;
    }

    public Category addCategory(Category category, String companyId) {

        if(validateCategory(category, companyId)) {
            return categoryRepository.save(category);
        } else {
            return null;
        }
    }
    public void deleteCategory(Category category, String companyId) {
        if(validateCategory(category, companyId)) {
            categoryRepository.delete(category);
        } else {
            return;
        }
    }

    public boolean validateCategory(Category category, String companyId) {

        if(category.getId() == null) {
            return true;
        }


        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        return (optionalCategory.isPresent() && optionalCategory.get().getCompany().getId().equals(companyId));
    }

    public Category getCategoryByPage(String companyId, Integer page) {
        Iterable<Category> categories = categoryRepository.findAllByCompanyId(companyId);
        List<Category> categoryList = new ArrayList<>();
        categories.forEach(categoryList::add);

        Category category = page < categoryList.size() ? categoryList.get(page) : null;
        category.setProducts(this.productService.getProducts(category.getId()));

        return category;
    }
}
