package knu.team7.syllabus.application.port.out;

import knu.team7.syllabus.application.port.in.command.ListCommand;
import knu.team7.syllabus.domain.model.SubjectSection;

import java.util.List;

public interface CreateSubjectSectionPort {
    List<SubjectSection> createSubjectSection(List<ListCommand> list);
}
