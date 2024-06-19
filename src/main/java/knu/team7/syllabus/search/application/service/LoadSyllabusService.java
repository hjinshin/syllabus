package knu.team7.syllabus.search.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.search.application.port.in.command.SyllabusCommand;
import knu.team7.syllabus.search.application.port.out.LoadSyllabusPort;
import knu.team7.syllabus.search.application.usecase.LoadSyllabusUseCase;
import knu.team7.syllabus.search.domain.model.Syllabus;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class LoadSyllabusService implements LoadSyllabusUseCase {
    private final LoadSyllabusPort loadSyllabusPort;
    @Override
    public Syllabus loadSyllabus(SyllabusCommand command) {
        return loadSyllabusPort.loadSyllabus(command);
    }
}
