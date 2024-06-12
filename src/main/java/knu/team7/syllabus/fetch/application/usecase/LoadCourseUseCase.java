package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.domain.model.Course;

import java.util.List;

public interface LoadCourseUseCase {
    List<Course> loadCourseByYearAndSeason(int year, String season);
}
