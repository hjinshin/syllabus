package knu.team7.syllabus.fetch.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.fetch.application.port.in.command.ScheduleCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateSchedulePort;
import knu.team7.syllabus.fetch.domain.model.SchedulePart;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.ScheduleJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.CourseRepository;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class SchedulePersistenceAdapter implements CreateSchedulePort {
    private final ScheduleRepository scheduleRepository;
    private final CourseRepository courseRepository;
    @Override
    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            retryFor = DataIntegrityViolationException.class
    )
    @Transactional
    public void createSchedule(List<ScheduleCommand> list) {
        Map<Long, CourseJpaEntity> courseMap = courseRepository.findAllById(
                list.stream().map(command -> command.course().getId()).collect(Collectors.toSet())
        ).stream().collect(Collectors.toMap(CourseJpaEntity::getId, course -> course));

        Map<SchedulePart, ScheduleJpaEntity> scheduleMap = scheduleRepository.findByCourseJpaEntityIn(
                new ArrayList<>(courseMap.values()))
                .stream().collect(
                        Collectors.toMap(entity -> SchedulePart.builder()
                                        .course_id(entity.getCourseJpaEntity().getId())
                                        .doPlan(entity.getDoPlan())
                                        .no(entity.getNo())
                                        .build()
                                , entity -> entity)
                );

        List<ScheduleJpaEntity> saveJpaEntities = list.stream().map(
                        command -> ScheduleJpaEntity.builder()
                                .no(command.no())
                                .lssnsGoalCntns(command.lssnsGoalCntns())
                                .lssnsMethd(command.lssnsMethd())
                                .rsrchCntns(command.rsrchCntns())
                                .weekSn(command.weekSn())
                                .weekNote(command.weekNote())
                                .doPlan(command.doPlan())
                                .courseJpaEntity(courseMap.get(command.course().getId()))
                                .build())
                .map(entity -> getExistOrNew(entity, scheduleMap))
                .filter(Objects::nonNull)
                .toList();

        scheduleRepository.saveAll(saveJpaEntities);
    }

    private ScheduleJpaEntity getExistOrNew(ScheduleJpaEntity entity, Map<SchedulePart, ScheduleJpaEntity> scheduleMap) {
        ScheduleJpaEntity existingEntity = scheduleMap.get(SchedulePart.builder()
                .course_id(entity.getCourseJpaEntity().getId())
                .doPlan(entity.getDoPlan())
                .no(entity.getNo())
                .build());


        if (existingEntity == null) {
            return entity;
        }

        if (Objects.equals(entity, existingEntity)) {
            return null;
        }
        System.out.println(entity);
        System.out.println(existingEntity);

        return existingEntity.toBuilder()
                .lssnsGoalCntns(entity.getLssnsGoalCntns())
                .lssnsMethd(entity.getLssnsMethd())
                .rsrchCntns(entity.getRsrchCntns())
                .weekSn(entity.getWeekSn())
                .weekNote(entity.getWeekNote())
                .build();
    }
}
