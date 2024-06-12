package knu.team7.syllabus.search.application.usecase;

import knu.team7.syllabus.search.application.port.in.command.SyllabusCommand;
import knu.team7.syllabus.search.domain.model.Syllabus;

public interface LoadSyllabusUseCase {
    Syllabus loadSyllabus(SyllabusCommand command);
}
