package knu.team7.syllabus.application.service;

import knu.team7.syllabus.application.port.in.command.ScheduleCommand;
import knu.team7.syllabus.application.port.out.CreateSchedulePort;
import knu.team7.syllabus.application.usecase.CreateScheduleUseCase;
import knu.team7.syllabus.core.annotation.UseCase;
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
