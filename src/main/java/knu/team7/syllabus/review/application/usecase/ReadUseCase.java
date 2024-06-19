package knu.team7.syllabus.review.application.usecase;

import knu.team7.syllabus.review.domain.model.Review;

import java.util.List;

public interface ReadUseCase {
    List<Review> readReviews(String crseNo, String profNm);
    List<Review> readReviewByUserId(String userId);

}
