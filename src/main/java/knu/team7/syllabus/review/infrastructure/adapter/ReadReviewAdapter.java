package knu.team7.syllabus.review.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.review.application.port.out.ReadPort;
import knu.team7.syllabus.review.domain.model.Review;
import knu.team7.syllabus.review.infrastructure.adapter.persistence.entity.ReivewJpaEntity;
import knu.team7.syllabus.review.infrastructure.adapter.persistence.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ReadReviewAdapter implements ReadPort {
    private final ReviewRepository reviewRepository;
    @Override
    public List<Review> readReviews(String crseNo, String profNm) {
        List<ReivewJpaEntity> entities = reviewRepository.findAllByCrseNoAndProfNm(crseNo, profNm);
        return entities.stream().map(
                entity -> Review.builder()
                        .year(entity.getYear())
                        .season(entity.getSeason())
                        .crseNo(entity.getCrseNo())
                        .profNm(entity.getProfNm())
                        .rating(entity.getRating())
                        .contents(entity.getContents())
                        .createdAt(entity.getCreatedAt())
                        .build()
        ).toList();
    }

    @Override
    public List<Review> readReviewByUserId(String userId) {
        List<ReivewJpaEntity> entities = reviewRepository.findAllByUserId(userId);
        return entities.stream().map(
                entity -> Review.builder()
                        .year(entity.getYear())
                        .season(entity.getSeason())
                        .crseNo(entity.getCrseNo())
                        .profNm(entity.getProfNm())
                        .rating(entity.getRating())
                        .contents(entity.getContents())
                        .createdAt(entity.getCreatedAt())
                        .build()
        ).toList();
    }
}
