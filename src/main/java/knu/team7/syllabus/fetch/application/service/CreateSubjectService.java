package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;
import knu.team7.syllabus.fetch.application.port.in.command.SubjectCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateSubjectPort;
import knu.team7.syllabus.fetch.application.usecase.CreateSubjectUseCase;
import knu.team7.syllabus.fetch.domain.model.Subject;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class CreateSubjectService implements CreateSubjectUseCase {
    private final CreateSubjectPort createSubjectPort;
    @Override
    public List<Subject> createSubject(List<LectureCommand> list) {
        Set<SubjectCommand> set = list.stream().map(
                command -> SubjectCommand.builder()
                        .sbjetCd(command.sbjetCd())
                        .sbjetNm(command.sbjetNm())
                        .build()
        ).collect(Collectors.toSet());
        return createSubjectPort.createSubject(set);
    }

}
