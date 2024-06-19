package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.application.port.in.command.DepartmentCommand;
import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateDepartmentPort;
import knu.team7.syllabus.fetch.application.usecase.CreateDepartmentUseCase;
import knu.team7.syllabus.fetch.domain.model.Department;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class CreateDepartmentService implements CreateDepartmentUseCase {
    private final CreateDepartmentPort createDepartmentPort;
    @Override
    public List<Department> createDepartment(List<LectureCommand> list) {
        Set<DepartmentCommand> set = list.stream().map(
                command -> DepartmentCommand.builder()
                        .college(command.estblUnivNm())
                        .depart(command.estblDprtnNm())
                        .build()).collect(Collectors.toSet());
        return createDepartmentPort.createDepartment(set);
    }
}
