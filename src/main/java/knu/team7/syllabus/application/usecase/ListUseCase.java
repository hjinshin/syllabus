package knu.team7.syllabus.application.usecase;

import knu.team7.syllabus.application.port.in.command.ListCommand;

import java.util.List;

public interface ListUseCase {
    List<ListCommand> getGEList() throws Exception;
    List<ListCommand> getOtherList() throws Exception;
}
