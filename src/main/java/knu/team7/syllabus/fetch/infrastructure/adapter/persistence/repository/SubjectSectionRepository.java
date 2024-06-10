package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.SubjectSectionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("FetchSubjectSectionRepository")

public interface SubjectSectionRepository extends JpaRepository<SubjectSectionJpaEntity, String> {
}
