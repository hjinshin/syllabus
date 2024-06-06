package knu.team7.syllabus.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.infrastructure.adapter.persistence.entity.SubjectCodeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectCodeRepository extends JpaRepository<SubjectCodeJpaEntity, String> {
}
