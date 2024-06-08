package knu.team7.syllabus.application.port.out;

import knu.team7.syllabus.application.port.in.command.CourseCommand;
import knu.team7.syllabus.domain.model.Course;

import java.util.List;

public interface CreateCoursePort {
    List<Course> createCourse(List<CourseCommand> list);
}
