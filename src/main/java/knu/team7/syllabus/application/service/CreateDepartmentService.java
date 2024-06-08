package knu.team7.syllabus.application.service;

import knu.team7.syllabus.application.port.in.command.DepartmentCommand;
import knu.team7.syllabus.application.port.out.CreateDepartmentPort;
import knu.team7.syllabus.application.usecase.CreateDepartmentUseCase;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.domain.model.Department;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CreateDepartmentService implements CreateDepartmentUseCase {
    private final CreateDepartmentPort createDepartmentPort;
    @Override
    public List<Department> createDepartment(List<DepartmentCommand> list) {
        return createDepartmentPort.createDepartment(list);
    }
}
