package knu.team7.syllabus.search.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.LectureJpaEntity;
import knu.team7.syllabus.search.infrastructure.adapter.persistence.repository.custom.LectureQueryDslRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("SearchLectureRepository")
public interface LectureRepository extends JpaRepository<LectureJpaEntity, Long>, LectureQueryDslRepositoryCustom {

}
