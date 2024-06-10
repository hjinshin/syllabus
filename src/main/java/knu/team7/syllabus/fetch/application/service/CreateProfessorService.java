package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.fetch.application.port.in.command.ProfessorCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateProfessorPort;
import knu.team7.syllabus.fetch.application.usecase.CreateProfessorUseCase;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.domain.model.Professor;
import lombok.RequiredArgsConstructor;

import java.util.*;

@UseCase
@RequiredArgsConstructor
public class CreateProfessorService implements CreateProfessorUseCase {
    private final CreateProfessorPort createProfessorPort;
    @Override
    public List<Professor> createProfessor(List<ProfessorCommand> list) {
        return createProfessorPort.createProfessor(list);
    }
}
