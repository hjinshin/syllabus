package knu.team7.syllabus.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.infrastructure.adapter.persistence.entity.LectureTimeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureTimeRepository extends JpaRepository<LectureTimeJpaEntity, Long> {
}
