package knu.team7.syllabus.application.usecase;

import knu.team7.syllabus.application.port.in.command.DepartmentCommand;
import knu.team7.syllabus.domain.model.Department;

import java.util.List;

public interface CreateDepartmentUseCase {
    List<Department> createDepartment(List<DepartmentCommand> list);

}
