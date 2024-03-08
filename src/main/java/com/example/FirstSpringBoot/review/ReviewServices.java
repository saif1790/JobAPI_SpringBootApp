package com.example.FirstSpringBoot.review;

import com.example.FirstSpringBoot.company.Company;

import java.util.List;


public interface ReviewServices
{
    List<Review> getAllReviews(Integer companyId);

    boolean  addReview(Review review,Integer companyId);

    Review getCompanyReviewByReviewId(Integer companyId,Integer reviewId);

    boolean updateCompanyReviewByReviewId(Integer companyId,Integer reviewId,Review review);

    boolean deleteReview(Integer companyId, Integer reviewId);
}
