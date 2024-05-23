package knu.team7.syllabus.application.usecase;

import com.google.gson.JsonObject;
import knu.team7.syllabus.application.port.in.command.CodeCommand;
import knu.team7.syllabus.domain.model.Search;

import java.util.List;

public interface ClassUseCase {
    public List<JsonObject> getClassList(List<CodeCommand> codeCommandList, String year, String season) throws Exception;
    public List<JsonObject> getClassList(String[] codeList, String year, String season) throws Exception;
    public Search getClass(String code, String year, String season);
}
