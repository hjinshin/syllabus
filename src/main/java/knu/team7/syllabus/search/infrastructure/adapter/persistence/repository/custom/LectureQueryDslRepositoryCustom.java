package knu.team7.syllabus.search.infrastructure.adapter.persistence.repository.custom;

import knu.team7.syllabus.search.application.port.in.command.LectureCommand;
import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.LectureJpaEntity;

import java.util.List;

public interface LectureQueryDslRepositoryCustom {
    List<LectureJpaEntity> findAllLectures(LectureCommand command);

}
