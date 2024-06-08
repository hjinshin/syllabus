package knu.team7.syllabus.application.usecase;

import knu.team7.syllabus.application.port.in.command.SyllabusCommand;

import java.util.List;

public interface CreateSyllabusUseCase {
    void createSyllabus(List<SyllabusCommand> list);
}
