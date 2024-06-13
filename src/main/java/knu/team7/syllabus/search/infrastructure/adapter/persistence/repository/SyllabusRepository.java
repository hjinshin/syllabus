package knu.team7.syllabus.search.infrastructure.adapter.persistence.repository;

import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.SyllabusJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("SearchSyllabusRepository")
public interface SyllabusRepository extends JpaRepository<SyllabusJpaEntity, Long> {

    @Query("SELECT s " +
            "FROM SearchSyllabusJpaEntity s " +
            "JOIN FETCH s.courseJpaEntity c " +
            "WHERE s.doPlan = :doPlan " +
            "AND c.crseNo = :crseNo AND c.season = :season AND c.year = :year")     // Join Fetch로 N+1 문제 해결
    Optional<SyllabusJpaEntity> findSyllabusJpaEntity(@Param("doPlan") String doPlan,
                                                     @Param("crseNo") String crseNo,
                                                     @Param("season") String season,
                                                     @Param("year") int year);

}
