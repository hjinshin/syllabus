package knu.team7.syllabus.search.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.search.application.port.in.command.ScheduleCommand;
import knu.team7.syllabus.search.application.port.out.LoadSchedulePort;
import knu.team7.syllabus.search.application.usecase.LoadScheduleUseCase;
import knu.team7.syllabus.search.domain.model.Schedule;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class LoadScheduleService implements LoadScheduleUseCase {
    private final LoadSchedulePort loadSchedulePort;
    @Override
    public List<Schedule> loadSchedules(ScheduleCommand command) {
        return loadSchedulePort.loadSchedules(command);
    }
}
