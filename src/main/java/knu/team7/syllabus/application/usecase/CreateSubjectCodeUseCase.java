package knu.team7.syllabus.application.usecase;

import knu.team7.syllabus.application.port.in.command.SubjectCodeCommand;
import knu.team7.syllabus.domain.model.SubjectCode;

import java.util.List;

public interface CreateSubjectCodeUseCase {
    List<SubjectCode> createSubjectCode(List<SubjectCodeCommand> list);
}
