package knu.team7.syllabus.application.usecase;

import knu.team7.syllabus.domain.model.TempSchedule;
import knu.team7.syllabus.domain.model.TempSyllabus;

import java.util.List;

public interface SyllabusUseCase {
    TempSyllabus getSyllabus(String year, String season, String subjectCode) throws Exception;
    List<TempSchedule> getSchedule(String year, String season, String subjectCode) throws Exception;
}
