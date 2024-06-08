package knu.team7.syllabus.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.infrastructure.adapter.persistence.entity.SubjectCodeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseJpaEntity, Long> {
    boolean existsByCrseNoAndYearAndSeasonAndSubjectCodeJpaEntity(String crseNo, int year, String season, SubjectCodeJpaEntity subjectCodeJpaEntity);
    CourseJpaEntity findByCrseNoAndYearAndSeasonAndSubjectCodeJpaEntity(String crseNo, int year, String season, SubjectCodeJpaEntity subjectCodeJpaEntity);
}
