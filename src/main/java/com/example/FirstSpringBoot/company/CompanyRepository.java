package com.example.FirstSpringBoot.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


public interface CompanyRepository extends JpaRepository<Company,Integer> {
}
