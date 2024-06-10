package knu.team7.syllabus.search.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.EvaluationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface EvaluationRepository extends JpaRepository<EvaluationJpaEntity, Long>, QuerydslPredicateExecutor<EvaluationJpaEntity> {
}
