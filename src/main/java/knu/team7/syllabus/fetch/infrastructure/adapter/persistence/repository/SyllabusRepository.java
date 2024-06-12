package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.SyllabusJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("FetchSyllabusRepository")
public interface SyllabusRepository extends JpaRepository<SyllabusJpaEntity, Long> {
    Optional<SyllabusJpaEntity> findByCourseJpaEntity(CourseJpaEntity courseJpaEntity);
    Optional<SyllabusJpaEntity> findByCourseJpaEntityAndDoPlan(CourseJpaEntity courseJpaEntity, String doPlan);
}
