package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;
import knu.team7.syllabus.fetch.domain.model.Subject;

import java.util.List;

public interface CreateSubjectUseCase {
    List<Subject> createSubject(List<LectureCommand> list);
}
