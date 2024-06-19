package knu.team7.syllabus.user.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.user.application.port.UpdateTablePort;
import knu.team7.syllabus.user.application.usecase.UpdateTableUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class UpdateTableService implements UpdateTableUseCase {
    private final UpdateTablePort updateTablePort;
    @Override
    public void updateUserTable(List<String> crseNos, int year, String season, String userId) {
        updateTablePort.updateUserTable(crseNos, year, season, userId);
    }
}
