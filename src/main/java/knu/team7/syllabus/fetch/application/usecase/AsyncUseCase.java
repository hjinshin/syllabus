package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.SyllabusCommand;
import knu.team7.syllabus.fetch.domain.model.Course;

import java.util.List;

public interface AsyncUseCase {
    void fetchAndCreateSchedule(List<Course> courseList, String option) throws Exception;

    void createEvaluation(List<List<SyllabusCommand>> list, String option);

    void createSyllabus(List<List<SyllabusCommand>> list, String option);
}
