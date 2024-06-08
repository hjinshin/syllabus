package knu.team7.syllabus.application.usecase;

import knu.team7.syllabus.application.port.in.command.EvaluationCommand;
import knu.team7.syllabus.domain.model.Evaluation;

import java.util.List;

public interface CreateEvaluationUseCase {
    List<Evaluation> createEvaluation(List<EvaluationCommand> list);
}
