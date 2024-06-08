package knu.team7.syllabus.application.port.out;

import knu.team7.syllabus.application.port.in.command.DepartmentCommand;
import knu.team7.syllabus.domain.model.Department;

import java.util.List;

public interface CreateDepartmentPort {
    List<Department> createDepartment(List<DepartmentCommand> list);

}
