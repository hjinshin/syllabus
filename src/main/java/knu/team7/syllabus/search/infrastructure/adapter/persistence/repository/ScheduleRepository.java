package knu.team7.syllabus.search.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.ScheduleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ScheduleRepository extends JpaRepository<ScheduleJpaEntity, Long>, QuerydslPredicateExecutor<ScheduleJpaEntity> {
}
