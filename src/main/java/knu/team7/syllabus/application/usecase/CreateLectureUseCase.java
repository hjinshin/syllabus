package knu.team7.syllabus.application.usecase;

import knu.team7.syllabus.application.port.in.command.LectureCommand;
import knu.team7.syllabus.domain.model.Lecture;

import java.util.List;

public interface CreateLectureUseCase {
    List<Lecture> createLecture(List<LectureCommand> list);
}
