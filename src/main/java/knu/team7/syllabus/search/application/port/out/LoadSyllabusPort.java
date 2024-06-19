package knu.team7.syllabus.search.application.port.out;

import knu.team7.syllabus.search.application.port.in.command.SyllabusCommand;
import knu.team7.syllabus.search.domain.model.Syllabus;

public interface LoadSyllabusPort {
    Syllabus loadSyllabus(SyllabusCommand command);
}
