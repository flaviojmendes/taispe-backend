package com.flaviojmendes.zapperson.controller;

import com.flaviojmendes.zapperson.model.Product;
import com.flaviojmendes.zapperson.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/product/{categoryId}")
    @ResponseBody
    public Iterable<Product> getProducts(@PathVariable String categoryId) {
        return productService.getProducts(categoryId);
    }

    @DeleteMapping(value = "/product/{companyId}")
    @ResponseBody
    public void deleteProducts(@RequestBody Product product,@PathVariable String companyId) {
        productService.deleteProduct(product, companyId);
    }

    @PostMapping(value = "/product/{companyId}")
    @ResponseBody
    public Product saveProduct(@RequestBody Product product, @PathVariable String companyId) {
        return productService.addProduct(product, companyId);
    }
}