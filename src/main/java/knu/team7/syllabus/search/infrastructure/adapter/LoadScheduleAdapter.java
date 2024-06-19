package knu.team7.syllabus.search.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.search.application.port.in.command.ScheduleCommand;
import knu.team7.syllabus.search.application.port.out.LoadSchedulePort;
import knu.team7.syllabus.search.domain.model.Schedule;
import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.ScheduleJpaEntity;
import knu.team7.syllabus.search.infrastructure.adapter.persistence.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadScheduleAdapter implements LoadSchedulePort {
    private final ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> loadSchedules(ScheduleCommand command) {
        List<ScheduleJpaEntity> scheduleJpaEntities = scheduleRepository.findScheduleJpaEntities(
                command.doPlan(), command.crseNo(), command.season(), command.year()
        );

        if (scheduleJpaEntities.size() == 0) {
            throw new IllegalArgumentException("schedule does not exist.");
        }


        return scheduleJpaEntities.stream().map(
                entity -> Schedule.builder()
                        .lssnsGoalCntns(entity.getLssnsGoalCntns())
                        .lssnsMethd(entity.getLssnsMethd())
                        .rsrchCntns(entity.getRsrchCntns())
                        .weekSn(entity.getWeekSn())
                        .weekNote(entity.getWeekNote())
                        .doPlan(entity.getDoPlan())
                        .build()
        ).toList();
    }
}
