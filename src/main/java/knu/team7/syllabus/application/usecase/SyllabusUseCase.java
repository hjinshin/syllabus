package knu.team7.syllabus.application.usecase;

import com.google.gson.JsonObject;

import java.util.List;

public interface SyllabusUseCase {
    String getSyllabus(String year, String season, String subjectCode) throws Exception;
    List<JsonObject> getSchedule(String year, String season, String subjectCode) throws Exception;
}
