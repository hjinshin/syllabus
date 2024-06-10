package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.fetch.application.port.in.command.CourseCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateCoursePort;
import knu.team7.syllabus.fetch.application.usecase.CreateCourseUseCase;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.domain.model.Course;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CreateCourseService implements CreateCourseUseCase {
    private final CreateCoursePort createCoursePort;
    @Override
    public List<Course> createCourse(List<CourseCommand> list) {
        return createCoursePort.createCourse(list);
    }
}
