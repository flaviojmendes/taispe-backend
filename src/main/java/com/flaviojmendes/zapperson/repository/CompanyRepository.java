package com.flaviojmendes.zapperson.repository;

import com.flaviojmendes.zapperson.model.Category;
import com.flaviojmendes.zapperson.model.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, String> {

    Company findByEmail(String email);

    Company findByUrl(String url);
}
