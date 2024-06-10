package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.SubjectCodeCommand;
import knu.team7.syllabus.fetch.domain.model.SubjectCode;

import java.util.List;

public interface CreateSubjectCodeUseCase {
    List<SubjectCode> createSubjectCode(List<SubjectCodeCommand> list);
}
