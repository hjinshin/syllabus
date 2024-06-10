package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.application.port.in.command.CourseCommand;
import knu.team7.syllabus.fetch.domain.model.Course;

import java.util.List;

public interface CreateCoursePort {
    List<Course> createCourse(List<CourseCommand> list);
}
