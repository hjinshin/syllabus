package knu.team7.syllabus.user.application.usecase;

import java.util.List;

public interface LoadTableUseCase {
    List<String> loadTables(int year, String season, String userId);
}
