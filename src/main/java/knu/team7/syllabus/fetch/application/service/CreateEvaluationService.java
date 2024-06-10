package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.fetch.application.port.in.command.EvaluationCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateEvaluationPort;
import knu.team7.syllabus.fetch.application.usecase.CreateEvaluationUseCase;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.domain.model.Evaluation;
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
