package knu.team7.syllabus.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<CourseJpaEntity, Long> {
    boolean existsByCrseNoAndYearAndSeason(String crseNo, int year, String season);
    CourseJpaEntity findByCrseNoAndYearAndSeason(String crseNo, int year, String season);
}
