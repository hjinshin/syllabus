package knu.team7.syllabus.application.port.out;

import knu.team7.syllabus.application.port.in.command.SubjectCodeCommand;

import java.util.List;

public interface CreateSubjectCodePort {
    void createSubjectCode(List<SubjectCodeCommand> list);
}
