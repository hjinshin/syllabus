package knu.team7.syllabus.application.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import knu.team7.core.Constants;
import knu.team7.core.annotation.UseCase;
import knu.team7.core.util.ApiUtil;
import knu.team7.core.util.GsonUtil;
import knu.team7.syllabus.application.port.in.command.GECodeCommand;
import knu.team7.syllabus.application.usecase.GEClassUseCase;
import knu.team7.syllabus.domain.model.Search;
import knu.team7.syllabus.domain.model.SearchClass;
import knu.team7.syllabus.domain.model.SearchPayload;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GEClassService implements GEClassUseCase {

    @Override
    public List<JsonObject> getGEClass(List<GECodeCommand> geCodeCommandList, String year, String season) throws Exception {
        List<JsonObject> list = new ArrayList<>();
        for (GECodeCommand item : geCodeCommandList) {
            Search search = SearchClass.builder()
                    .estblYear(year)
                    .estblSmstrSctcd(season)
                    .sbjetRelmCd(item.codeId())
                    .gubun("01")
                    .isApi("Y")
                    .build();
            String response = requestClass(search);
            list.addAll(parsingData(response));
//            System.out.println(GsonUtil.fromJson(response));
        }
        return list;
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
