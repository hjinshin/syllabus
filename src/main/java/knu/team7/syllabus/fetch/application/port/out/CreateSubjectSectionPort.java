package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.application.port.in.command.ListCommand;
import knu.team7.syllabus.fetch.domain.model.SubjectSection;

import java.util.List;

public interface CreateSubjectSectionPort {
    List<SubjectSection> createSubjectSection(List<ListCommand> list);
}
