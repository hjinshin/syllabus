package knu.team7.syllabus.review.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.review.application.port.out.ReadPort;
import knu.team7.syllabus.review.application.usecase.ReadUseCase;
import knu.team7.syllabus.review.domain.model.Review;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ReadService implements ReadUseCase {
    private final ReadPort readPort;
    @Override
    public List<Review> readReviews(String crseNo, String profNm) {
        return readPort.readReviews(crseNo, profNm);
    }

    @Override
    public List<Review> readReviewByUserId(String userId) {
        return readPort.readReviewByUserId(userId);
    }
}
