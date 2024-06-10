package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.fetch.application.port.in.command.ListCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateSubjectSectionPort;
import knu.team7.syllabus.fetch.application.usecase.CreateSubjectSectionUseCase;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.domain.model.SubjectSection;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CreateSubjectSectionService implements CreateSubjectSectionUseCase {
    private final CreateSubjectSectionPort createSubjectSectionPort;
    @Override
    public List<SubjectSection> createSubjectSection(List<ListCommand> list) {

        return createSubjectSectionPort.createSubjectSection(list);
    }
}
