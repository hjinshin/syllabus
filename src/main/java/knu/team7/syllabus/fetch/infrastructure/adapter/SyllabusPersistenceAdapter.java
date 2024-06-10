package knu.team7.syllabus.fetch.infrastructure.adapter;

import knu.team7.syllabus.fetch.application.port.in.command.SyllabusCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateSyllabusPort;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.SubjectCodeJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.SyllabusJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.SyllabusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class SyllabusPersistenceAdapter implements CreateSyllabusPort {
    private final SyllabusRepository syllabusRepository;
    @Override
    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            retryFor = DataIntegrityViolationException.class
    )
    @Transactional
    public void createSyllabus(List<SyllabusCommand> list) {
        Set<SyllabusJpaEntity> saveJpaEntities = list.stream().map(
                        command -> SyllabusJpaEntity.builder()
                                .crseGoal(command.crseGoal())
                                .eduGoal(command.eduGoal())
                                .summary(command.summary())
                                .textbook(command.textbook())
                                .evalMethd(command.evalMethd())
                                .intviTimeLoc(command.intviTimeLoc())
                                .refer(command.refer())
                                .doPlan(command.doPlan())
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
                ).filter(entity -> !syllabusRepository.existsByCourseJpaEntityAndDoPlan(
                        entity.getCourseJpaEntity(), entity.getDoPlan()))
                .collect(Collectors.toSet());

        syllabusRepository.saveAll(saveJpaEntities);
    }
}
