package knu.team7.syllabus.search.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.SubjectSectionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SubjectSectionRepository extends JpaRepository<SubjectSectionJpaEntity, String>, QuerydslPredicateExecutor<SubjectSectionJpaEntity> {
}
