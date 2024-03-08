package com.example.FirstSpringBoot.company.impl;

import com.example.FirstSpringBoot.company.Company;
import com.example.FirstSpringBoot.company.CompanyRepository;
import com.example.FirstSpringBoot.company.CompanyServices;
import com.example.FirstSpringBoot.job.Job;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServicesImpl implements CompanyServices {

    private CompanyRepository companyRepository;

    public CompanyServicesImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public String createCompany(Company company) {
        companyRepository.save(company);
        return "Job Created Successfully!";
    }

    @Override
    public Company findCompanyById(Integer id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteCompanyById(Integer id) {

        try {
            Company company = companyRepository.findById(id).orElse(null);
            if (company != null) {
                companyRepository.deleteById(id);
                return "Company Deleted";
            } else {
                return "Company Id Not found";
            }
        } catch (Exception e) {
            return "Company Id Not found";
        }
    }

    @Override
    public String updateCompanyById(Company company, Integer id) {

        Optional<Company> optionalCompany = companyRepository.findById(id);

        if (optionalCompany.isPresent()) {
            Company existingCompany = optionalCompany.get();
            System.out.println("Hello existing job");
            existingCompany.setCompanyTitle(company.getCompanyTitle());
            existingCompany.setCompanyDescription(company.getCompanyDescription());
            existingCompany.setJobs(company.getJobs());
            companyRepository.save(existingCompany);
            return "ID with " + id + "Company has been updated";
        }
        return null;
    }
}
