package knu.team7.syllabus.fetch.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.fetch.application.port.in.command.ScheduleCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateSchedulePort;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.ScheduleJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.SubjectCodeJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class SchedulePersistenceAdapter implements CreateSchedulePort {
    private final ScheduleRepository scheduleRepository;
    @Override
    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            retryFor = DataIntegrityViolationException.class
    )
    @Transactional
    public void createSchedule(List<ScheduleCommand> list) {
        List<ScheduleJpaEntity> saveJpaEntities = list.stream().map(
                        command -> ScheduleJpaEntity.builder()
                                .lssnsGoalCntns(command.lssnsGoalCntns())
                                .lssnsMethd(command.lssnsMethd())
                                .rsrchCntns(command.rsrchCntns())
                                .weekSn(command.weekSn())
                                .weekNote(command.weekNote())
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
                                .build())
                .filter(entity -> !scheduleRepository.existsByCourseJpaEntityAndDoPlan(entity.getCourseJpaEntity(), entity.getDoPlan()))
                .toList();
        scheduleRepository.saveAll(saveJpaEntities);
    }
}
