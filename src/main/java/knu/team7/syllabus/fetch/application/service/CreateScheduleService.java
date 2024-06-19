package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.application.port.in.command.ScheduleCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateSchedulePort;
import knu.team7.syllabus.fetch.application.usecase.CreateScheduleUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CreateScheduleService implements CreateScheduleUseCase {
    private final CreateSchedulePort createSchedulePort;
    @Override
    public void createSchedule(List<ScheduleCommand> list) {
        createSchedulePort.createSchedule(list);
    }
}
