package knu.team7.syllabus.search.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.LectureTimeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface LectureTimeRepository extends JpaRepository<LectureTimeJpaEntity, Long>, QuerydslPredicateExecutor<LectureTimeJpaEntity> {
}