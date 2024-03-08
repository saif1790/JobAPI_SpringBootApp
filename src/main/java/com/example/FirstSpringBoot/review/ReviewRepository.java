package com.example.FirstSpringBoot.review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

    List<Review> findByCompanyId(Integer companyId);
}
