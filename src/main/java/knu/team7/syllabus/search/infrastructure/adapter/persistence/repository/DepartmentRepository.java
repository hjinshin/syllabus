package knu.team7.syllabus.search.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.DepartmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DepartmentRepository extends JpaRepository<DepartmentJpaEntity, Long>, QuerydslPredicateExecutor<DepartmentJpaEntity> {
}
