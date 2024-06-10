package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.ListCommand;

import java.util.List;

public interface ListUseCase {
    List<ListCommand> getGEList() throws Exception;
    List<ListCommand> getOtherList() throws Exception;
}
