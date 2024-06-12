package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;

import java.util.List;

public interface CreateLectureUseCase {
    void createLecture(List<LectureCommand> list);
}
