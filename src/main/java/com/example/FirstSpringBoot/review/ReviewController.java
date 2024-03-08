package com.example.FirstSpringBoot.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewServices reviewServices;

    public ReviewController(ReviewServices reviewServices) {
        this.reviewServices = reviewServices;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Integer companyId)
    {
        return new ResponseEntity<>(reviewServices.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/review")
    public ResponseEntity<String> addReview(@RequestBody Review review,@PathVariable Integer companyId)
    {
         Boolean idReviewedSaved = reviewServices.addReview(review,companyId);
         if(idReviewedSaved) {
             return new ResponseEntity<>("Review added Successfully!!", HttpStatus.CREATED);
         }else {
             return new ResponseEntity<>("Company does not exist with this id :" + companyId, HttpStatus.NOT_FOUND);
         }
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getCompanyReviewByReviewId(@PathVariable Integer companyId, @PathVariable Integer reviewId)
    {
        Review review = reviewServices.getCompanyReviewByReviewId(companyId,reviewId);
        if(review != null)
            return new ResponseEntity<>(reviewServices.getCompanyReviewByReviewId(companyId,reviewId),HttpStatus.OK);
        return new ResponseEntity("Review for this company is not exist with reviewId :"+ reviewId,HttpStatus.OK);

    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Boolean> updateReview(@PathVariable Integer companyId, @PathVariable Integer reviewId,@RequestBody Review review)
    {
        boolean isReviewUpdated = reviewServices.updateCompanyReviewByReviewId(companyId,reviewId,review);
        if(isReviewUpdated)
            return new ResponseEntity("Reiew updated successfully",HttpStatus.OK);
        return new ResponseEntity("Reiew not updated successfully",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Boolean> deleteCompanyReviewByReviewId(@PathVariable Integer companyId, @PathVariable Integer reviewId)
    {
       Boolean isReviewDeleted = reviewServices.deleteReview(companyId,reviewId);
        if(isReviewDeleted)
            return new ResponseEntity("Reiew deleted successfully",HttpStatus.OK);
        return new ResponseEntity("Reiew not deleted",HttpStatus.NOT_FOUND);
    }

}
