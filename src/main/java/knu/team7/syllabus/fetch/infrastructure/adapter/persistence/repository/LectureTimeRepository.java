package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.LectureTimeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("FetchLectureTimeRepository")
public interface LectureTimeRepository extends JpaRepository<LectureTimeJpaEntity, Long> {
}
