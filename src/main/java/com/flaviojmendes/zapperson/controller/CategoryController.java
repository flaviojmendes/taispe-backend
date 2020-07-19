package com.flaviojmendes.zapperson.controller;

import com.flaviojmendes.zapperson.model.Category;
import com.flaviojmendes.zapperson.model.Product;
import com.flaviojmendes.zapperson.service.CategoryService;
import com.flaviojmendes.zapperson.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/category/{companyId}")
    @ResponseBody
    public Iterable<Category> getCategory(@PathVariable String companyId) {
        return categoryService.getCategories(companyId);
    }

    @GetMapping(value = "/category/{companyId}/{page}")
    @ResponseBody
    public Category getCategory(@PathVariable String companyId, @PathVariable Integer page) {
        return categoryService.getCategoryByPage(companyId, page);
    }

    @DeleteMapping(value = "/category/{companyId}")
    @ResponseBody
    public void deleteCategories(@RequestBody Category category, @PathVariable String companyId) {
        categoryService.deleteCategory(category, companyId);
    }

    @PostMapping(value = "/category/{companyId}")
    @ResponseBody
    public Category saveCategory(@RequestBody Category category, @PathVariable String companyId) {
        return categoryService.addCategory(category, companyId);
    }
}
