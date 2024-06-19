package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.ScheduleJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("FetchScheduleRepository")
public interface ScheduleRepository extends JpaRepository<ScheduleJpaEntity, Long>{

    List<ScheduleJpaEntity> findByCourseJpaEntityIn(List<CourseJpaEntity> courseJpaEntity);
}
