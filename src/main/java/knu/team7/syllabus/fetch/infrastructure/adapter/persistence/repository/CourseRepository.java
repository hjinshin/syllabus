package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("CourseRepository")
public interface CourseRepository extends JpaRepository<CourseJpaEntity, Long> {
    Optional<CourseJpaEntity> findByCrseNoAndYearAndSeason(String crseNo, int year, String season);
    boolean existsByCrseNoAndYearAndSeason(String crseNo, int year, String season);

    List<CourseJpaEntity> findAllByYearAndSeason(int year, String season);
}
