package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.EvaluationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("FetchEvaluationRepository")
public interface EvaluationRepository extends JpaRepository<EvaluationJpaEntity, Long> {
    boolean existsByCourseJpaEntity(CourseJpaEntity courseJpaEntity);
    EvaluationJpaEntity findByCourseJpaEntity(CourseJpaEntity courseJpaEntity);
}
