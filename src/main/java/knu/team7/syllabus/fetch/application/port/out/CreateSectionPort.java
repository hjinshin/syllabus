package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.application.port.in.command.SectionCommand;
import knu.team7.syllabus.fetch.domain.model.Section;

import java.util.List;
import java.util.Set;

public interface CreateSectionPort {
    List<Section> createSection(Set<SectionCommand> set);
}
