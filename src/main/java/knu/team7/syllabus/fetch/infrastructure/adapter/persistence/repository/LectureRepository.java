package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.LectureJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("FetchLectureRepository")
public interface LectureRepository extends JpaRepository<LectureJpaEntity, Long> {
//    boolean existsByCourseJpaEntity(CourseJpaEntity courseJpaEntity);
    Optional<LectureJpaEntity> findByCourseJpaEntity(CourseJpaEntity courseJpaEntity);
}
