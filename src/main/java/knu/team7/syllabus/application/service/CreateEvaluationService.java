package knu.team7.syllabus.application.service;

import knu.team7.syllabus.application.port.in.command.EvaluationCommand;
import knu.team7.syllabus.application.port.out.CreateEvaluationPort;
import knu.team7.syllabus.application.usecase.CreateEvaluationUseCase;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.domain.model.Evaluation;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CreateEvaluationService implements CreateEvaluationUseCase {
    private final CreateEvaluationPort createEvaluationPort;
    @Override
    public List<Evaluation> createEvaluation(List<EvaluationCommand> list) {
        return createEvaluationPort.createEvaluation(list);
    }
}
