package knu.team7.syllabus.application.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import knu.team7.core.Constants;
import knu.team7.core.annotation.UseCase;
import knu.team7.core.util.ApiUtil;
import knu.team7.core.util.GsonUtil;
import knu.team7.syllabus.application.port.in.command.CodeCommand;
import knu.team7.syllabus.application.usecase.ClassUseCase;
import knu.team7.syllabus.domain.model.Search;
import knu.team7.syllabus.domain.model.SearchClass;
import knu.team7.syllabus.domain.model.SearchPayload;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ClassService implements ClassUseCase {

    @Override
    public List<JsonObject> getClassList(List<CodeCommand> codeCommandList, String year, String season) throws Exception {
        List<JsonObject> list = new ArrayList<>();
        for (CodeCommand item : codeCommandList) {
            Search search = getClass(item.codeId(), year, season);
            String response = requestClass(search);
            list.addAll(parsingData(response));
        }
        return list;
    }

    @Override
    public List<JsonObject> getClassList(String[] codeList, String year, String season) throws Exception {
        List<JsonObject> list = new ArrayList<>();
        for (String code : codeList) {
            Search search = getClass(code, year, season);
            String response = requestClass(search);
            list.addAll(parsingData(response));
        }
        return list;
    }
    @Override
    public Search getClass(String code, String year, String season) {
        Search search = SearchClass.builder()
                .estblYear(year)
                .estblSmstrSctcd(season)
                .sbjetSctcd2(code)
                .gubun("01")
                .isApi("Y")
                .build();
        System.out.println(search);
        return search;
    }


    private String requestClass(Search search) throws Exception {
            return ApiUtil.post(
                Constants.CLASSURL,
                GsonUtil.toJson(SearchPayload.builder().search(search).build()),
                null);
    }

    private List<JsonObject> parsingData(String jsonData) {
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
