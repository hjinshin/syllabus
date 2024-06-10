package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;
import knu.team7.syllabus.fetch.domain.model.Lecture;

import java.util.List;

public interface CreateLectureUseCase {
    List<Lecture> createLecture(List<LectureCommand> list);
}
