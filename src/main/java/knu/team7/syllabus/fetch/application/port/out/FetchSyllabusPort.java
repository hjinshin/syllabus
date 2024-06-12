package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.application.port.in.command.SyllabusCommand;

public interface FetchSyllabusPort {
    SyllabusCommand fetchSyllabus(String year, String season, String crseNo, String doPlan) throws Exception;
}
