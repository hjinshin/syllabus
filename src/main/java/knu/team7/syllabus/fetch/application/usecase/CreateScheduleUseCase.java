package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.ScheduleCommand;

import java.util.List;

public interface CreateScheduleUseCase {
    void createSchedule(List<ScheduleCommand> list);
}
