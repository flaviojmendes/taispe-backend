package com.flaviojmendes.zapperson.repository;

import com.flaviojmendes.zapperson.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface ProductRepository extends CrudRepository<Product, String> {

    List<Product> findAllByCategoryId(String companyId);

}
