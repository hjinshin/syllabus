package knu.team7.syllabus.application.port.out;

import knu.team7.syllabus.application.port.in.command.ProfessorCommand;
import knu.team7.syllabus.domain.model.Professor;

import java.util.List;

public interface CreateProfessorPort {
    List<Professor> createProfessor(List<ProfessorCommand> list);
}
