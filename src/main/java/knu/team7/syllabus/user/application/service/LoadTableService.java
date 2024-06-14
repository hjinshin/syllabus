package knu.team7.syllabus.user.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.user.application.port.LoadTablePort;
import knu.team7.syllabus.user.application.usecase.LoadTableUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class LoadTableService implements LoadTableUseCase {
    private final LoadTablePort loadTablePort;
    @Override
    public List<String> loadTables(int year, String season, String userId) {
        return loadTablePort.loadTables(year, season, userId);
    }
}
