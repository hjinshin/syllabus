package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;
import knu.team7.syllabus.fetch.domain.model.Department;

import java.util.List;

public interface CreateDepartmentUseCase {
    List<Department> createDepartment(List<LectureCommand> list);

}
