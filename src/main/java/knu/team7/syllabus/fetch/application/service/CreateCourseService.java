package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.application.port.in.command.CourseCommand;
import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateCoursePort;
import knu.team7.syllabus.fetch.application.usecase.CreateCourseUseCase;
import knu.team7.syllabus.fetch.domain.model.Course;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class CreateCourseService implements CreateCourseUseCase {
    private final CreateCoursePort createCoursePort;
    @Override
    public List<Course> createCourse(List<LectureCommand> list) {
        Set<CourseCommand> set = list.stream().map(
                command -> CourseCommand.builder()
                        .crseNo(command.crseNo())
                        .year(Integer.parseInt(command.estblYear()))
                        .season(command.estblSmstrSctnm())
                        .subjectCd(command.sbjetCd())
                        .build()
        ).collect(Collectors.toSet());

        return createCoursePort.createCourse(set);
    }
}
