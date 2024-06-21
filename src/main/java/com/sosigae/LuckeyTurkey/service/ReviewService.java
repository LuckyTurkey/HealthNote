package com.sosigae.LuckeyTurkey.service;

import com.sosigae.LuckeyTurkey.domain.Review;
import com.sosigae.LuckeyTurkey.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    // 병원 조회 -> 전체 리뷰 목록
    public List<Review> getReviewByHospitalId(int hospitalId){
        return reviewRepository.findAllByHospitalId(hospitalId);
    }

    // 리뷰 조회
    public Review getReviewById(int reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    // 리뷰 저장
    public void saveReview(Review review){
        review.setReviewDate(LocalDate.now());
        reviewRepository.save(review);
    }

    // 리뷰 삭제
    public void deleteReview(int reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
