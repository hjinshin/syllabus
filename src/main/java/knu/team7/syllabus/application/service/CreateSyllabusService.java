package knu.team7.syllabus.application.service;

import knu.team7.syllabus.application.port.in.command.SyllabusCommand;
import knu.team7.syllabus.application.port.out.CreateSyllabusPort;
import knu.team7.syllabus.application.usecase.CreateSyllabusUseCase;
import knu.team7.syllabus.core.annotation.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CreateSyllabusService implements CreateSyllabusUseCase {
    private final CreateSyllabusPort createSyllabusPort;
    @Override
    public void createSyllabus(List<SyllabusCommand> list) {
        createSyllabusPort.createSyllabus(list);
    }
}
