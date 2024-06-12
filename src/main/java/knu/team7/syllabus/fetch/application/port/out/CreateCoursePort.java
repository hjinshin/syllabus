package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.application.port.in.command.CourseCommand;
import knu.team7.syllabus.fetch.domain.model.Course;

import java.util.List;
import java.util.Set;

public interface CreateCoursePort {
    List<Course> createCourse(Set<CourseCommand> set);
}
