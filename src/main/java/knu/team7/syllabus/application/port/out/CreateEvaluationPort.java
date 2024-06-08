package knu.team7.syllabus.application.port.out;

import knu.team7.syllabus.application.port.in.command.EvaluationCommand;
import knu.team7.syllabus.domain.model.Evaluation;

import java.util.List;

public interface CreateEvaluationPort {
    List<Evaluation> createEvaluation(List<EvaluationCommand> list);
}
