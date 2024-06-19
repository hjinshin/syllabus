package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.application.port.in.command.SyllabusCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateSyllabusPort;
import knu.team7.syllabus.fetch.application.usecase.CreateSyllabusUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CreateSyllabusService implements CreateSyllabusUseCase {
    private final CreateSyllabusPort createSyllabusPort;
    @Override
    public void createSyllabus(List<List<SyllabusCommand>> list) {
        createSyllabusPort.createSyllabus(list);
    }
}
