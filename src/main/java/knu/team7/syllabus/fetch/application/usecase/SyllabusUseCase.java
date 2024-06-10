package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.service.dto.in.SyllabusParamsCommand;
import knu.team7.syllabus.fetch.domain.model.Course;
import knu.team7.syllabus.fetch.domain.model.Syllabus;

import java.util.List;

public interface SyllabusUseCase {
    List<Syllabus> getSyllabusList(List<SyllabusParamsCommand> commandList, List<Course> courseList) throws Exception;
    Syllabus getSyllabus(String year, String season, String subjectCode, Course course, String doPlan) throws Exception;

}
