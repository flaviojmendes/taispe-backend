package com.flaviojmendes.zapperson.service;

import com.flaviojmendes.zapperson.model.Category;
import com.flaviojmendes.zapperson.model.Company;
import com.flaviojmendes.zapperson.model.Product;
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
        Iterable<Category> categories = categoryRepository.findAllByCompanyIdOrderByOrderAsc(companyId);

        // This is to sort null orders.


        Category maxCategory = categoryRepository.findFirstByCompanyIdOrderByOrderDesc(companyId);
        int i = maxCategory != null && maxCategory.getOrder() != null ? maxCategory.getOrder() + 1 : 1;
        for(Category category : categories) {
            if(category.getOrder() == null) {
                category.setOrder(i);
                i++;
            }

        };

        this.categoryRepository.saveAll(categories);

        Iterable<Category> categoriesReordered = categoryRepository.findAllByCompanyIdOrderByOrderAsc(companyId);


        return categoriesReordered;
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
        Iterable<Category> categories = categoryRepository.findAllByCompanyIdOrderByOrderAsc(companyId);
        List<Category> categoryList = new ArrayList<>();
        categories.forEach(categoryList::add);

        Category category = page < categoryList.size() ? categoryList.get(page) : null;
        if (category != null) {
            category.setProducts(this.productService.getProducts(category.getId()));
        }

        return category;
    }

    public boolean setCategoryOrderUp(Category category, String companyId) {
        if(category.getOrder() == 1) {
            return false;
        }

        Category categoryToReorder = this.categoryRepository.findByOrderAndCompanyId(category.getOrder()-1, companyId);
        categoryToReorder.setOrder(category.getOrder());
        this.categoryRepository.save(categoryToReorder);

        category.setOrder(category.getOrder() - 1);
        this.categoryRepository.save(category);

        return true;
    }

    public boolean setCategoryOrderDown(Category category, String companyId) {
        Category categoryToReorder = this.categoryRepository.findByOrderAndCompanyId(category.getOrder()+1, companyId);
        if(categoryToReorder == null) {
            return false;
        }

        categoryToReorder.setOrder(category.getOrder());
        this.categoryRepository.save(categoryToReorder);

        category.setOrder(category.getOrder() + 1);
        this.categoryRepository.save(category);

        return true;
    }



}
