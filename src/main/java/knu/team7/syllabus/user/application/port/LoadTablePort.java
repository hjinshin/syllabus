package knu.team7.syllabus.user.application.port;

import java.util.List;

public interface LoadTablePort {
    List<String> loadTables(int year, String season, String userId);
}
