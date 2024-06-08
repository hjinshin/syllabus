package knu.team7.syllabus.application.usecase;

import knu.team7.syllabus.application.port.in.command.CourseCommand;
import knu.team7.syllabus.domain.model.Course;

import java.util.List;

public interface CreateCourseUseCase {
    List<Course> createCourse(List<CourseCommand> list);
}
