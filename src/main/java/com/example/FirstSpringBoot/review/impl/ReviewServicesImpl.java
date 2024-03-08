package com.example.FirstSpringBoot.review.impl;

import com.example.FirstSpringBoot.company.Company;
import com.example.FirstSpringBoot.company.CompanyRepository;
import com.example.FirstSpringBoot.company.CompanyServices;
import com.example.FirstSpringBoot.review.Review;
import com.example.FirstSpringBoot.review.ReviewRepository;
import com.example.FirstSpringBoot.review.ReviewServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServicesImpl implements ReviewServices {
    private ReviewRepository reviewRepository;
    private CompanyServices companyServices;

    public ReviewServicesImpl(ReviewRepository reviewRepository, CompanyServices companyServices) {
        this.reviewRepository = reviewRepository;
        this.companyServices = companyServices;
    }

    @Override
    public List<Review> getAllReviews(Integer companyId) {
        Company company = companyServices.findCompanyById(companyId);
        if (company != null) {
            List<Review> reviews = reviewRepository.findByCompanyId(companyId);
            return reviews;
        }
        return null;
    }

    @Override
    public boolean addReview(Review review, Integer companyId) {
        Company company = companyServices.findCompanyById(companyId);
        if (company != null) {
            ;
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getCompanyReviewByReviewId(Integer companyId, Integer reviewId) {

        List<Review> companyReviews = reviewRepository.findByCompanyId(companyId);

        for (Review review : companyReviews) {
            if (reviewId.equals(review.getId())) {
                return review;
            }
        }
        return null;
    }

    @Override
    public boolean updateCompanyReviewByReviewId(Integer companyId, Integer reviewId, Review updatedReview) {
        List<Review> companyReviews = reviewRepository.findByCompanyId(companyId);
        for (Review review : companyReviews) {
            if (reviewId.equals(review.getId())) {
                review.setReviewTitle(updatedReview.getReviewTitle());
                review.setReviewDescription((updatedReview.getReviewDescription()));
                review.setRating(updatedReview.getRating());
                review.setCompany(companyServices.findCompanyById(companyId));
                reviewRepository.save(review);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteReview(Integer companyId, Integer reviewId) {

        List<Review> companyReviews = reviewRepository.findByCompanyId(companyId);
        for (Review review : companyReviews) {
            if (reviewId.equals(review.getId())) {
                reviewRepository.delete(review);
                return true;
            }
        }
        return false;
    }
}
