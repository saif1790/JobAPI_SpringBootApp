package com.example.FirstSpringBoot.company;


import com.example.FirstSpringBoot.job.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

private CompanyServices companyServices;

    public CompanyController(CompanyServices companyServices) {
        this.companyServices = companyServices;
    }

    @GetMapping
    public ResponseEntity<List<Company>> findAll(){
        return ResponseEntity.ok(companyServices.getAllCompanies());
    }

    @PostMapping
    public ResponseEntity<String> createCompany(@RequestBody Company company){
        System.out.println(company.getCompanyTitle());
        companyServices.createCompany(company);
        return new ResponseEntity<>("Company Created Successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findCompanyById(@PathVariable Integer id){
        Company company = companyServices.findCompanyById(id);
        if(company != null)
          return new ResponseEntity<>(company,HttpStatus.OK);
        else
            return new ResponseEntity<>(company,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    /*@DeleteMapping("jobs/{id}")*/
    public ResponseEntity<String> deleteCompanyById(@PathVariable Integer id)
    {
        String deleteMsg = companyServices.deleteCompanyById(id);
        if(deleteMsg.equals("Company Deleted"))
            return new ResponseEntity<>("Company Deleted",HttpStatus.OK);
        return new ResponseEntity<>("Company Id Not found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    /* @RequestMapping(value = "/{id}", method = RequestMethod.PUT)*///at method level,it define url that this method supposed to be mapped too
    public ResponseEntity<String> updateCompanyById(@RequestBody Company updatedCompany, @PathVariable Integer id)
    {
        String updateMsg = companyServices.updateCompanyById(updatedCompany,id);
        if(updateMsg.contains("Company has been updated"))
            return new ResponseEntity<>("Company Updated",HttpStatus.OK);
        return new ResponseEntity<>("Company Id Not found",HttpStatus.NOT_FOUND);
    }

}
