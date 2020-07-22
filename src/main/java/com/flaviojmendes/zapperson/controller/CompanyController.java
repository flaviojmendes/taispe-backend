package com.flaviojmendes.zapperson.controller;

import com.flaviojmendes.zapperson.exception.DuplicatedEntityException;
import com.flaviojmendes.zapperson.exception.TaispeException;
import com.flaviojmendes.zapperson.model.Category;
import com.flaviojmendes.zapperson.model.Company;
import com.flaviojmendes.zapperson.service.CategoryService;
import com.flaviojmendes.zapperson.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping(value = "/company")
    @ResponseBody
    public ResponseEntity<Iterable<Company>> getCompany() {
        return new ResponseEntity<>(companyService.getCompanies(), HttpStatus.OK);
    }

    @PostMapping(value = "/company/email")
    @ResponseBody
    public ResponseEntity<Company> getCompanyByEmail(@RequestBody String companyEmail) {
        return new ResponseEntity<>(companyService.getCompanyByEmail(companyEmail), HttpStatus.OK);
    }
    @PostMapping(value = "/company/url")
    @ResponseBody
    public ResponseEntity<Company> getCompanyByUrl(@RequestBody String companyUrl) {
        return new ResponseEntity<>(companyService.getCompanyByUrl(companyUrl), HttpStatus.OK);
    }

    @DeleteMapping(value = "/company")
    @ResponseBody
    public void deleteCategories(@RequestBody Company company) {
        companyService.deleteCompany(company);
    }

    @PostMapping(value = "/company")
    @ResponseBody
    public ResponseEntity<Company> saveCompany(@RequestBody Company company) {
        try {
            return new ResponseEntity<>(companyService.addCompany(company), HttpStatus.CREATED);
        } catch (TaispeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
