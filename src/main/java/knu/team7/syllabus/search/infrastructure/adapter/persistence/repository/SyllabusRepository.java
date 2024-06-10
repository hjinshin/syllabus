package knu.team7.syllabus.search.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.SyllabusJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SyllabusRepository extends JpaRepository<SyllabusJpaEntity, Long>, QuerydslPredicateExecutor<SyllabusJpaEntity> {
}
