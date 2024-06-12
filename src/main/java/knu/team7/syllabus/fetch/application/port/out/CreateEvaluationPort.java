package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.application.port.in.command.EvaluationCommand;

import java.util.List;

public interface CreateEvaluationPort {
    void createEvaluation(List<EvaluationCommand> list);
}
