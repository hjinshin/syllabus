package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.DepartmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("FetchDepartmentRepository")
public interface DepartmentRepository extends JpaRepository<DepartmentJpaEntity, Long> {
    boolean existsByCollegeAndDepart(String college, String depart);
    DepartmentJpaEntity findByCollegeAndDepart(String college, String depart);
}
