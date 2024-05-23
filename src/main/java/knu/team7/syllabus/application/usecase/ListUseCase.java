package knu.team7.syllabus.application.usecase;

import knu.team7.syllabus.application.port.in.command.CodeCommand;

import java.util.List;

public interface ListUseCase {
    List<CodeCommand> getGEList() throws Exception;
    List<CodeCommand> getSubjectList() throws Exception;
}
