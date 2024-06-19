package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.application.port.in.command.EvaluationCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateEvaluationPort;
import knu.team7.syllabus.fetch.application.usecase.CreateEvaluationUseCase;
import lombok.RequiredArgsConstructor;

import java.util.LinkedHashSet;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CreateEvaluationService implements CreateEvaluationUseCase {
    private final CreateEvaluationPort createEvaluationPort;
    @Override
    public void createEvaluation(List<EvaluationCommand> list) {
        createEvaluationPort.createEvaluation(new LinkedHashSet<>(list));
    }
}
