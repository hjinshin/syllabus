package knu.team7.syllabus.application.usecase;

import com.google.gson.JsonObject;
import knu.team7.syllabus.application.port.in.command.GECodeCommand;

import java.util.List;

public interface GEClassUseCase {
    public List<JsonObject> getGEClass(List<GECodeCommand> geCodeCommandList, String year, String season) throws Exception;
}
