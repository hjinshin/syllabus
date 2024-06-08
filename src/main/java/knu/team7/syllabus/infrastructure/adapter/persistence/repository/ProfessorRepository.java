package knu.team7.syllabus.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.infrastructure.adapter.persistence.entity.ProfessorJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<ProfessorJpaEntity, String> {
    boolean existsByProfNmAndProfTelAndProfEmail(String profNm, String profTel, String profEmail);
    ProfessorJpaEntity findByProfNmAndProfTelAndProfEmail(String profNm, String profTel, String profEmail);
}
