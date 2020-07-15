package com.flaviojmendes.zapperson.controller;

import com.flaviojmendes.zapperson.model.Category;
import com.flaviojmendes.zapperson.model.Company;
import com.flaviojmendes.zapperson.service.CategoryService;
import com.flaviojmendes.zapperson.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/company")
    @ResponseBody
    public Iterable<Company> getCompany() {
        return companyService.getCompanies();
    }

    @PostMapping(value = "/company/email")
    @ResponseBody
    public Company getCompanyByEmail(@RequestBody String companyEmail) {
        return companyService.getCompanyByEmail(companyEmail);
    }
    @PostMapping(value = "/company/url")
    @ResponseBody
    public Company getCompanyByUrl(@RequestBody String companyUrl) {
        return companyService.getCompanyByUrl(companyUrl);
    }

    @DeleteMapping(value = "/company")
    @ResponseBody
    public void deleteCategories(@RequestBody Company company) {
        companyService.deleteCompany(company);
    }

    @PostMapping(value = "/company")
    @ResponseBody
    public Company saveCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }
}
