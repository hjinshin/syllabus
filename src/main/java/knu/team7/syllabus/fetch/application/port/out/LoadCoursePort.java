package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.domain.model.Course;

import java.util.List;

public interface LoadCoursePort {
    List<Course> loadCourseByYearAndSeason(int year, String season);

}
