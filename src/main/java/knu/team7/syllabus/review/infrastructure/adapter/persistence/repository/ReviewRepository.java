package knu.team7.syllabus.review.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.review.infrastructure.adapter.persistence.entity.ReivewJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReivewJpaEntity, Long> {
    boolean existsByCrseNoAndProfNmAndUserId(String crseNo, String profNm, String userId);
    List<ReivewJpaEntity> findAllByCrseNoAndProfNm(String crseNo, String profNm);

    List<ReivewJpaEntity> findAllByUserId(String userId);
}
