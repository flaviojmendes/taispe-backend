package com.flaviojmendes.zapperson.service;

import com.flaviojmendes.zapperson.model.Category;
import com.flaviojmendes.zapperson.model.Company;
import com.flaviojmendes.zapperson.repository.CategoryRepository;
import com.flaviojmendes.zapperson.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Iterable<Company> getCompanies() {
        return companyRepository.findAll();
    }

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }
    public void deleteCompany(Company company) {
        companyRepository.delete(company);
    }

    public Company getCompanyById(String companyId) {
        return companyRepository.findById(companyId).get();
    }

    public Company getCompanyByEmail(String email) {
        return companyRepository.findByEmail(email);
    }

    public Company getCompanyByUrl(String url) {
        return companyRepository.findByUrl(url);
    }
}
