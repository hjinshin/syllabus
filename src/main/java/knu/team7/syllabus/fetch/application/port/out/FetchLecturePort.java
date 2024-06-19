package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.application.port.in.command.external.Search;

public interface FetchLecturePort {
    String requestLecture(Search search) throws Exception;
}
