package knu.team7.syllabus.search.application.port.out;

import knu.team7.syllabus.search.domain.model.Lecture;
import knu.team7.syllabus.search.application.port.in.command.LectureCommand;

import java.util.List;

public interface LoadLecturePort {
    List<Lecture> loadLectures(LectureCommand command);
}
