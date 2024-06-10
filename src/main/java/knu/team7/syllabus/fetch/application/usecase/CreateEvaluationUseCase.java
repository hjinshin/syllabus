package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.EvaluationCommand;
import knu.team7.syllabus.fetch.domain.model.Evaluation;

import java.util.List;

public interface CreateEvaluationUseCase {
    List<Evaluation> createEvaluation(List<EvaluationCommand> list);
}
