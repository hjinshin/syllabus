package knu.team7.syllabus.user.application.port;

import java.util.List;

public interface UpdateTablePort {
    void updateUserTable(List<String> crseNos, int year, String season, String userId);
}
