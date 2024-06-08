package knu.team7.syllabus.application.service;

import knu.team7.syllabus.application.port.in.command.ProfessorCommand;
import knu.team7.syllabus.application.port.out.CreateProfessorPort;
import knu.team7.syllabus.application.usecase.CreateProfessorUseCase;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.domain.model.Professor;
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
