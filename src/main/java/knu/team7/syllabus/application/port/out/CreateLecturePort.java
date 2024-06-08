package knu.team7.syllabus.application.port.out;

import knu.team7.syllabus.application.port.in.command.LectureCommand;
import knu.team7.syllabus.domain.model.Lecture;

import java.util.List;

public interface CreateLecturePort {
    List<Lecture> createLecture(List<LectureCommand> list);

}
