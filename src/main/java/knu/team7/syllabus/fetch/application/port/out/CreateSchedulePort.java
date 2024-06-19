package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.application.port.in.command.ScheduleCommand;

import java.util.List;

public interface CreateSchedulePort {
    void createSchedule(List<ScheduleCommand> list);

}
