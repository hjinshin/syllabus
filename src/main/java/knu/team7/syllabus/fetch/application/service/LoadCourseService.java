package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.application.port.out.LoadCoursePort;
import knu.team7.syllabus.fetch.application.usecase.LoadCourseUseCase;
import knu.team7.syllabus.fetch.domain.model.Course;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class LoadCourseService implements LoadCourseUseCase {
    private final LoadCoursePort loadCoursePort;
    @Override
    public List<Course> loadCourseByYearAndSeason(int year, String season) {
        return loadCoursePort.loadCourseByYearAndSeason(year, season);
    }
}
