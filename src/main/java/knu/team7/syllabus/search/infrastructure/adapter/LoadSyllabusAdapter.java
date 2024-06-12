package knu.team7.syllabus.search.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.search.application.port.in.command.SyllabusCommand;
import knu.team7.syllabus.search.application.port.out.LoadSyllabusPort;
import knu.team7.syllabus.search.domain.model.Syllabus;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadSyllabusAdapter implements LoadSyllabusPort {
    @Override
    public Syllabus loadSyllabus(SyllabusCommand command) {

        return null;
    }
}
