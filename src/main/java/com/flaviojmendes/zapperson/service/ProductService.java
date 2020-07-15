package com.flaviojmendes.zapperson.service;

import com.flaviojmendes.zapperson.model.Category;
import com.flaviojmendes.zapperson.model.Product;
import com.flaviojmendes.zapperson.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts(String categoryId) {
        return productRepository.findAllByCategoryId(categoryId);
    }

    public Product addProduct(Product product, String companyId) {
        if(validateProduct(product, companyId)) {
            return productRepository.save(product);
        } else {
            return null;
        }
    }
    public void deleteProduct(Product product, String companyId) {
        if(validateProduct(product, companyId)) {
            productRepository.delete(product);
        } else {
            return;
        }
    }


    public boolean validateProduct(Product product, String companyId) {

        if(product.getId() == null) {
            return true;
        }

        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        return (optionalProduct.isPresent() && optionalProduct.get().getCategory().getCompany().getId().equals(companyId));
    }
}
