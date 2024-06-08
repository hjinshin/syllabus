package knu.team7.syllabus.application.port.out;

import knu.team7.syllabus.application.port.in.command.ScheduleCommand;

import java.util.List;

public interface CreateSchedulePort {
    void createSchedule(List<ScheduleCommand> list);

}
