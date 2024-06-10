package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.application.port.in.command.ProfessorCommand;
import knu.team7.syllabus.fetch.domain.model.Professor;

import java.util.List;

public interface CreateProfessorPort {
    List<Professor> createProfessor(List<ProfessorCommand> list);
}
