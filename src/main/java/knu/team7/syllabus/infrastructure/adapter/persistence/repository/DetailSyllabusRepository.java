package knu.team7.syllabus.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.infrastructure.adapter.persistence.entity.DetailSyllabusJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailSyllabusRepository extends JpaRepository<DetailSyllabusJpaEntity, Long> {
}
