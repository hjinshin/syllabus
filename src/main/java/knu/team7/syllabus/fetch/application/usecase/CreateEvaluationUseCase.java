package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.EvaluationCommand;

import java.util.List;

public interface CreateEvaluationUseCase {
    void createEvaluation(List<EvaluationCommand> list);
}
