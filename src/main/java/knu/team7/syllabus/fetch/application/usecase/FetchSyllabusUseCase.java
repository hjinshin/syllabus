package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;
import knu.team7.syllabus.fetch.application.port.in.command.SyllabusCommand;

import java.util.List;

public interface FetchSyllabusUseCase {
    List<List<SyllabusCommand>> fetchSyllabus(List<LectureCommand> lecList) throws Exception;

    SyllabusCommand fetchSyllabus(String year, String season, String crseNo, String doPlan) throws Exception;
}
