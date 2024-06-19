package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.application.port.in.command.EvaluationCommand;

import java.util.Set;

public interface CreateEvaluationPort {
    void createEvaluation(Set<EvaluationCommand> list);
}
