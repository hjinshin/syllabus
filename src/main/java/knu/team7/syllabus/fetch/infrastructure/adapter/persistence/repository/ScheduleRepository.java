package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.ScheduleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("FetchScheduleRepository")
public interface ScheduleRepository extends JpaRepository<ScheduleJpaEntity, Long>{
    boolean existsByCourseJpaEntityAndDoPlan(CourseJpaEntity courseJpaEntity, String doPlan);
}
