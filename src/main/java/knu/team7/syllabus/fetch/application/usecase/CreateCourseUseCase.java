package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;
import knu.team7.syllabus.fetch.domain.model.Course;

import java.util.List;

public interface CreateCourseUseCase {
    List<Course> createCourse(List<LectureCommand> list);
}
