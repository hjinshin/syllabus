package knu.team7.syllabus.review.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.review.application.port.in.WriteCommand;
import knu.team7.syllabus.review.application.port.out.WritePort;
import knu.team7.syllabus.review.infrastructure.adapter.persistence.entity.ReivewJpaEntity;
import knu.team7.syllabus.review.infrastructure.adapter.persistence.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@PersistenceAdapter
@RequiredArgsConstructor
public class ReviewPersistenceAdapter implements WritePort {
    private final ReviewRepository reviewRepository;
    @Override
    public void writeReview(WriteCommand command) {
        if (reviewRepository.existsByCrseNoAndProfNmAndUserId(command.crseNo(), command.profNm(), command.userId())) {
            throw new IllegalArgumentException("User already written a review");
        }

        reviewRepository.save(ReivewJpaEntity.builder()
                        .userId(command.userId())
                        .year(command.year())
                        .season(command.season())
                        .crseNo(command.crseNo())
                        .profNm(command.profNm())
                        .rating(command.rating())
                        .contents(command.contents())
                        .createdAt(LocalDateTime.now())
                .build());
    }
}
