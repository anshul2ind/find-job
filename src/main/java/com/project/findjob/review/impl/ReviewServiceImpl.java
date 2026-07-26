package com.project.findjob.review.impl;

import com.project.findjob.company.Company;
import com.project.findjob.company.CompanyService;
import com.project.findjob.review.Review;
import com.project.findjob.review.ReviewRepository;
import com.project.findjob.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    @Override
    public List<Review> findAllByCompanyId(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean create(Long companyId, Review review) {
        Company company = companyService.getById(companyId);
        if(company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getById(Long id) {
        return null;
    }

    @Override
    public Review getByCompanyIdAndReviewId(Long companyId, Long reviewId) {
        return reviewRepository.findByCompanyIdAndId(companyId, reviewId).orElse(null);
    }

    @Override
    public boolean deleteByCompanyIdAndReviewId(Long companyId, Long reviewId) {
        if(reviewRepository.existsByCompanyIdAndId(companyId, reviewId)) {
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateByCompanyIdAndReviewId(Long companyId, Long reviewId, Review review) {
        var reviewEntity = reviewRepository.findByCompanyIdAndId(companyId, reviewId).orElse(null);
        if(reviewEntity != null) {
            reviewEntity.setTitle(review.getTitle());
            reviewEntity.setDescription(review.getDescription());
            reviewEntity.setRating(review.getRating());
            reviewRepository.save(reviewEntity);
            return true;
        }
        return false;
    }
}
