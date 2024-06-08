package knu.team7.syllabus.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.infrastructure.adapter.persistence.entity.LectureJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<LectureJpaEntity, Long> {
    boolean existsByCourseJpaEntity(CourseJpaEntity courseJpaEntity);
    LectureJpaEntity findByCourseJpaEntity(CourseJpaEntity courseJpaEntity);
}
