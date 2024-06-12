package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.core.util.ParseUtil;
import knu.team7.syllabus.fetch.application.port.in.command.SectionCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateSectionPort;
import knu.team7.syllabus.fetch.application.usecase.CreateSectionUseCase;
import knu.team7.syllabus.fetch.domain.model.Category;
import knu.team7.syllabus.fetch.domain.model.Section;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class CreateSectionService implements CreateSectionUseCase {
    private final CreateSectionPort createSectionPort;
    @Override
    public List<Section> createSection(List<Category> list) {
        Set<SectionCommand> set = list.stream().map(
                command -> SectionCommand.builder()
                        .codeId(ParseUtil.getCode(command))
                        .sctNm(command.getCategory())
                        .lSct(command.getLCodeNm())
                        .mSct(command.getMCodeNm())
                        .sSct(command.getSCodeNm())
                        .build()
        ).collect(Collectors.toSet());

        return createSectionPort.createSection(set);
    }
}
