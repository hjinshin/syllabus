package knu.team7.syllabus.infrastructure.adapter;

import knu.team7.syllabus.application.port.in.command.ScheduleCommand;
import knu.team7.syllabus.application.port.out.CreateSchedulePort;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.infrastructure.adapter.persistence.entity.ScheduleJpaEntity;
import knu.team7.syllabus.infrastructure.adapter.persistence.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class SchedulePersistenceAdapter implements CreateSchedulePort {
    private final ScheduleRepository scheduleRepository;
    @Override
    public void createSchedule(List<ScheduleCommand> list) {
        List<ScheduleJpaEntity> saveJpaEntities = list.stream().map(
                        command -> ScheduleJpaEntity.builder()
                                .lssnsGoalCntns(command.lssnsGoalCntns())
                                .lssnsMethd(command.lssnsMethd())
                                .rsrchCntns(command.rsrchCntns())
                                .weekSn(command.weekSn())
                                .weekNote(command.weekNote())
                                .courseJpaEntity(CourseJpaEntity.builder()
                                        .id(command.course().getId())
                                        .crseNo(command.course().getCrseNo())
                                        .year(Integer.parseInt(command.course().getYear()))
                                        .season(command.course().getSeason())
                                        .build())
                                .build())
                .filter(entity -> !scheduleRepository.existsByCourseJpaEntity(entity.getCourseJpaEntity()))
                .toList();
        scheduleRepository.saveAll(saveJpaEntities);
    }
}
