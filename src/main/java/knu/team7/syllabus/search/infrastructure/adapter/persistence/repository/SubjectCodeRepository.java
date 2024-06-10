package knu.team7.syllabus.search.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.SubjectCodeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SubjectCodeRepository extends JpaRepository<SubjectCodeJpaEntity, String>, QuerydslPredicateExecutor<SubjectCodeJpaEntity> {
}
