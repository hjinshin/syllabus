package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;

import java.util.List;

public interface CreateLecturePort {
    void createLecture(List<LectureCommand> list);

}
