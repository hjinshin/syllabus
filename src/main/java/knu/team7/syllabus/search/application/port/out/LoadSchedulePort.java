package knu.team7.syllabus.search.application.port.out;

import knu.team7.syllabus.search.application.port.in.command.ScheduleCommand;
import knu.team7.syllabus.search.domain.model.Schedule;

import java.util.List;

public interface LoadSchedulePort {
    List<Schedule> loadSchedules(ScheduleCommand command);
}
