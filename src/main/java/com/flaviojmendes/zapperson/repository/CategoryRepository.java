package com.flaviojmendes.zapperson.repository;

import com.flaviojmendes.zapperson.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, String> {

    Iterable<Category> findAllByCompanyId(String companyId);
}
