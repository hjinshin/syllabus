package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.ScheduleCommand;
import knu.team7.syllabus.fetch.domain.model.Course;

import java.util.List;

public interface FetchScheduleUseCase {
    List<ScheduleCommand> fetchSchedule(List<Course> courseList) throws Exception;
    List<ScheduleCommand> fetchScheduleByDoPlan(Course course, String doPlan) throws Exception;
}
