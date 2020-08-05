package com.flaviojmendes.zapperson.repository;

import com.flaviojmendes.zapperson.model.Category;
import com.flaviojmendes.zapperson.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductRepository extends CrudRepository<Product, String> {

    List<Product> findAllByCategoryId(String companyId);

    Product findByOrderAndCategoryId(int i, String categoryId);

    List<Product> findAllByCategoryIdOrderByOrderAsc(String categoryId);

    Product findFirstByCategoryIdOrderByOrderDesc(String categoryId);
}
