package knu.team7.syllabus.user.infrostructure.adapter.persistence.repository;

import knu.team7.syllabus.user.infrostructure.adapter.persistence.entity.TableJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TimeTableRepository extends JpaRepository<TableJpaEntity, Long> {
    Optional<TableJpaEntity> findByYearAndSeasonAndUserId(int year, String season, String userId);
}
