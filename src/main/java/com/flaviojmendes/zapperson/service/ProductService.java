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

        Iterable<Product> products = productRepository.findAllByCategoryIdOrderByOrderAsc(categoryId);

        // This is to sort null orders.


        Product maxProduct = productRepository.findFirstByCategoryIdOrderByOrderDesc(categoryId);
        int i = maxProduct != null && maxProduct.getOrder() != null ? maxProduct.getOrder() + 1 : 1;
        for(Product product : products) {
            if(product.getOrder() == null) {
                product.setOrder(i);
                i++;
            }

        };

        this.productRepository.saveAll(products);

        List<Product> productsReordered = productRepository.findAllByCategoryIdOrderByOrderAsc(categoryId);


        return productsReordered;

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

    public boolean setProductOrderUp(Product product, String categoryId) {
        if(product.getOrder() == 1) {
            return false;
        }

        Product productToReorder = this.productRepository.findByOrderAndCategoryId(product.getOrder()-1, categoryId);
        productToReorder.setOrder(product.getOrder());
        this.productRepository.save(productToReorder);

        product.setOrder(product.getOrder() - 1);
        this.productRepository.save(product);

        return true;
    }

    public boolean setProductOrderDown(Product product, String categoryId) {
        Product productToReorder = this.productRepository.findByOrderAndCategoryId(product.getOrder()+1, categoryId);
        if(productToReorder == null) {
            return false;
        }

        productToReorder.setOrder(product.getOrder());
        this.productRepository.save(productToReorder);

        product.setOrder(product.getOrder() + 1);
        this.productRepository.save(product);

        return true;
    }
}
