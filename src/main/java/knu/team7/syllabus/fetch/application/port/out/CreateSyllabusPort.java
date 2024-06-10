package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.application.port.in.command.SyllabusCommand;

import java.util.List;

public interface CreateSyllabusPort {
    void createSyllabus(List<SyllabusCommand> list);
}
