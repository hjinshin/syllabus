package knu.team7.syllabus.application.port.out;

import knu.team7.syllabus.application.port.in.command.SyllabusCommand;

import java.util.List;

public interface CreateSyllabusPort {
    void createSyllabus(List<SyllabusCommand> list);
}
