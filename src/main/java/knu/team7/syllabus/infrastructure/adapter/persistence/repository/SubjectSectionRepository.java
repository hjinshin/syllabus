package knu.team7.syllabus.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.infrastructure.adapter.persistence.entity.SubjectSectionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectSectionRepository extends JpaRepository<SubjectSectionJpaEntity, Long> {
}
