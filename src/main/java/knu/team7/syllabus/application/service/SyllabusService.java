package knu.team7.syllabus.application.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import knu.team7.core.Constants;
import knu.team7.core.annotation.UseCase;
import knu.team7.core.util.ApiUtil;
import knu.team7.core.util.GsonUtil;
import knu.team7.syllabus.application.usecase.SyllabusUseCase;
import knu.team7.syllabus.domain.model.Search;
import knu.team7.syllabus.domain.model.SearchPayload;
import knu.team7.syllabus.domain.model.SearchSyllabus;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class SyllabusService implements SyllabusUseCase {
    @Override
    public String getSyllabus(String year, String season, String subjectCode) throws Exception {
        Search search = SearchSyllabus.builder()
                .estblYear(year)
                .estblSmstrSctcd(season)
                .sbjetCd(subjectCode)
                .sbjetDvnno("001")
                .lctreLnggeSctcd(season.replace("CMBS", "STCU"))
                .isApi("Y")
                .build();
        String response = requestSyllabus(search, Constants.SYLLABUS_URL);
        return parsingSyllabusData(response);
    }

    @Override
    public List<JsonObject> getSchedule(String year, String season, String subjectCode) throws Exception {
        Search search = SearchSyllabus.builder()
                .estblYear(year)
                .estblSmstrSctcd(season)
                .sbjetCd(subjectCode)
                .sbjetDvnno("001")
                .lctreLnggeSctcd(season.replace("CMBS", "STCU"))
                .isApi("Y")
                .build();
        String response = requestSyllabus(search, Constants.SCHEDULE_URL);
        return parsingScheduleData(response);
    }

    private String requestSyllabus(Search search, String url) throws Exception {
        return ApiUtil.post(
                url,
                GsonUtil.toJson(SearchPayload.builder()
                        .search(search)
                        .build()),
                null);
    }

    private String parsingSyllabusData(String jsonData) {
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonObject dataObject = GsonUtil.getAsJsonObject(jsonObject, "data");
        return GsonUtil.toJson(dataObject);
    }

    private List<JsonObject> parsingScheduleData(String jsonData) {
        List<JsonObject> codes = new ArrayList<>();
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonArray codesArray = GsonUtil.getAsJsonArray(jsonObject, "data");
        for (JsonElement jsonElement : codesArray) {
            JsonObject item = jsonElement.getAsJsonObject();
            codes.add(item);
        }
        return codes;
    }
}
