package com.example.FirstSpringBoot.company;


import com.example.FirstSpringBoot.job.Job;
import com.example.FirstSpringBoot.review.Review;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "company_title")
    private String companyTitle;

    @Column(name = "company_description")
    private String companyDescription;

    //one company has created multiple job
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    public Company(Integer id, String companyTitle, String companyDescription, List<Job> jobs, List<Review> reviews) {
        this.id = id;
        this.companyTitle = companyTitle;
        this.companyDescription = companyDescription;
        this.jobs = jobs;
        this.reviews = reviews;
    }

    //one company have multiple review
    @OneToMany(mappedBy = "company")
    private List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Company() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyTitle() {
        return companyTitle;
    }

    public void setCompanyTitle(String companyTitle) {
        this.companyTitle = companyTitle;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
