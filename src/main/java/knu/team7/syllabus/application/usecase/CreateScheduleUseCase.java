package knu.team7.syllabus.application.usecase;

import knu.team7.syllabus.application.port.in.command.ScheduleCommand;

import java.util.List;

public interface CreateScheduleUseCase {
    void createSchedule(List<ScheduleCommand> list);
}