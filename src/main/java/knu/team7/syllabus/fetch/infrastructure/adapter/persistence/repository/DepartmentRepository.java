package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.DepartmentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("FetchDepartmentRepository")
public interface DepartmentRepository extends JpaRepository<DepartmentJpaEntity, Long> {
    Optional<DepartmentJpaEntity> findByCollegeAndDepart(String college, String depart);
}
