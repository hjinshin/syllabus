package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.application.port.in.command.DepartmentCommand;
import knu.team7.syllabus.fetch.domain.model.Department;

import java.util.List;
import java.util.Set;

public interface CreateDepartmentPort {
    List<Department> createDepartment(Set<DepartmentCommand> list);

}
