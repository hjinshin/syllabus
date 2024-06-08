package knu.team7.syllabus.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.infrastructure.adapter.persistence.entity.DepartmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentJpaEntity, Long> {
    boolean existsByCollegeAndDepart(String college, String depart);
    DepartmentJpaEntity findByCollegeAndDepart(String college, String depart);
}
