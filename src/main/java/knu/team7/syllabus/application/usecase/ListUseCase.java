package knu.team7.syllabus.application.usecase;

import knu.team7.syllabus.application.port.in.command.CodeCommand;

import java.util.List;

public interface ListUseCase {
    public List<CodeCommand> getGEList() throws Exception;
    public List<CodeCommand> getSubjectList() throws Exception;
}
