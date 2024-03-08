package com.example.FirstSpringBoot.company;

import com.example.FirstSpringBoot.job.Job;

import java.util.List;

public interface CompanyServices {

    List<Company> getAllCompanies();

    String createCompany(Company company);

    Company findCompanyById(Integer id);

    String deleteCompanyById(Integer id);

    String updateCompanyById(Company company,Integer Id);
}
