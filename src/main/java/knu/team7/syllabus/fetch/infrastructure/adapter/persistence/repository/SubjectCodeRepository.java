package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.SubjectCodeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("FetchSubjectCodeRepository")
public interface SubjectCodeRepository extends JpaRepository<SubjectCodeJpaEntity, String> {
}
