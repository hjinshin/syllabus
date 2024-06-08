package knu.team7.syllabus.infrastructure.adapter;

import knu.team7.syllabus.application.port.in.command.SyllabusCommand;
import knu.team7.syllabus.application.port.out.CreateSyllabusPort;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.infrastructure.adapter.persistence.entity.SubjectCodeJpaEntity;
import knu.team7.syllabus.infrastructure.adapter.persistence.entity.SyllabusJpaEntity;
import knu.team7.syllabus.infrastructure.adapter.persistence.repository.SyllabusRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class SyllabusPersistenceAdapter implements CreateSyllabusPort {
    private final SyllabusRepository syllabusRepository;
    @Override
    public void createSyllabus(List<SyllabusCommand> list) {
        List<SyllabusJpaEntity> saveJpaEntities = list.stream().map(
                        command -> SyllabusJpaEntity.builder()
                                .crseGoal(command.crseGoal())
                                .eduGoal(command.eduGoal())
                                .summary(command.summary())
                                .textbook(command.textbook())
                                .evalMethd(command.evalMethd())
                                .intviTimeLoc(command.intviTimeLoc())
                                .refer(command.refer())
                                .courseJpaEntity(CourseJpaEntity.builder()
                                        .id(command.course().getId())
                                        .crseNo(command.course().getCrseNo())
                                        .year(Integer.parseInt(command.course().getYear()))
                                        .season(command.course().getSeason())
                                        .subjectCodeJpaEntity(SubjectCodeJpaEntity.builder()
                                                .sbjetCd(command.course().getSubjectCode().getSbjetCd())
                                                .sbjetNm(command.course().getSubjectCode().getSbjetNm())
                                                .build())
                                        .build())
                                .build()
                ).filter(entity -> !syllabusRepository.existsByCourseJpaEntity(entity.getCourseJpaEntity()))
                .toList();
        syllabusRepository.saveAll(saveJpaEntities);
    }
}
