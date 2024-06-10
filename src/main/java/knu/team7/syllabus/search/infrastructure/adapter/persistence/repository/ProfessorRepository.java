package knu.team7.syllabus.search.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.ProfessorJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ProfessorRepository extends JpaRepository<ProfessorJpaEntity, String>, QuerydslPredicateExecutor<ProfessorJpaEntity> {
}
