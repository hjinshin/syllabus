package knu.team7.syllabus.search.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.LectureJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface LectureRepository extends JpaRepository<LectureJpaEntity, Long>, QuerydslPredicateExecutor<LectureJpaEntity> {
}
