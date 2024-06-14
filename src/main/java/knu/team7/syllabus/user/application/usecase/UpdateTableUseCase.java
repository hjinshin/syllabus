package knu.team7.syllabus.user.application.usecase;

import java.util.List;

public interface UpdateTableUseCase {
    void updateUserTable(List<String> crseNos, int year, String season, String userId);
}
