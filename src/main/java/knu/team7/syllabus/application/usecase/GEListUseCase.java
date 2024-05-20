package knu.team7.syllabus.application.usecase;

import knu.team7.syllabus.application.port.in.command.GECodeCommand;

import java.util.List;

public interface GEListUseCase {
    public List<GECodeCommand> getGEList(String estblYear) throws Exception;
}
