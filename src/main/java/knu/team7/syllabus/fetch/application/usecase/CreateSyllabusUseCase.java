package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.SyllabusCommand;

import java.util.List;

public interface CreateSyllabusUseCase {
    void createSyllabus(List<List<SyllabusCommand>> list);
}
