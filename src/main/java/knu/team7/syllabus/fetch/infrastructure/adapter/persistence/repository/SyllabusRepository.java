package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.SyllabusJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("FetchSyllabusRepository")
public interface SyllabusRepository extends JpaRepository<SyllabusJpaEntity, Long> {
    boolean existsByCourseJpaEntityAndDoPlan(CourseJpaEntity courseJpaEntity, String doPlan);
}
