package knu.team7.syllabus.search.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CourseRepository extends JpaRepository<CourseJpaEntity, Long>, QuerydslPredicateExecutor<CourseJpaEntity> {
}
