package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.fetch.application.port.in.command.SubjectCodeCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateSubjectCodePort;
import knu.team7.syllabus.fetch.application.usecase.CreateSubjectCodeUseCase;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.domain.model.SubjectCode;
import lombok.RequiredArgsConstructor;

import java.util.*;

@UseCase
@RequiredArgsConstructor
public class CreateSubjectCodeService implements CreateSubjectCodeUseCase {
    private final CreateSubjectCodePort createSubjectCodePort;
    @Override
    public List<SubjectCode> createSubjectCode(List<SubjectCodeCommand> list) {
        createSubjectCodePort.createSubjectCode(list);
        return list.stream().map(
                item -> SubjectCode.builder()
                        .sbjetCd(item.sbjetCd())
                        .sbjetNm(item.sbjetNm())
                        .build()
        ).toList();
    }

}
