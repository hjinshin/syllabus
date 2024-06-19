package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.application.port.in.command.SubjectCommand;
import knu.team7.syllabus.fetch.domain.model.Subject;

import java.util.List;
import java.util.Set;

public interface CreateSubjectPort {
    List<Subject> createSubject(Set<SubjectCommand> set);
}
