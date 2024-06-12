package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.application.port.in.command.ScheduleCommand;
import knu.team7.syllabus.fetch.domain.model.Course;

import java.util.List;

public interface FetchSchedulePort {
    List<ScheduleCommand> fetchSchedule(Course course, String doPlan) throws Exception;
}
