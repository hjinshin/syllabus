package knu.team7.syllabus.application.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import knu.team7.core.Constants;
import knu.team7.core.annotation.UseCase;
import knu.team7.core.util.ApiUtil;
import knu.team7.core.util.GsonUtil;
import knu.team7.syllabus.application.port.in.command.GECodeCommand;
import knu.team7.syllabus.domain.model.Search;
import knu.team7.syllabus.domain.model.SearchList;
import knu.team7.syllabus.domain.model.SearchPayload;
import knu.team7.syllabus.application.usecase.GEListUseCase;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GEListService implements GEListUseCase {
    @Override
    public List<GECodeCommand> getGEList(String estblYear) throws Exception {
        List<GECodeCommand> list = new ArrayList<>();
        for (String gelist_key : Constants.GELIST_KEYS) {
            Search search = SearchList.builder()
                    .key(gelist_key)
                    .build();
            String response = requestList(search);
            list.addAll(parsingData(response));
        }
        return list;
    }

    private String requestList(Search search) throws Exception {
        return ApiUtil.post(
                Constants.LISTURL,
                GsonUtil.toJson(SearchPayload.builder().search(search).build()),
                null);
    }

    private List<GECodeCommand> parsingData(String jsonData) {
        List<GECodeCommand> codes = new ArrayList<>();
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonObject dataObject = GsonUtil.getAsJsonObject(jsonObject, "data");
        JsonObject optionObject = GsonUtil.getAsJsonObject(dataObject, "option");
        JsonArray codesArray = GsonUtil.getAsJsonArray(optionObject, "codes");
        for (JsonElement jsonElement : codesArray) {
            addItem(codes, jsonElement);
        }

        return codes;
    }

    private void addItem(List<GECodeCommand> list, JsonElement jsonElement) {
        JsonObject item = jsonElement.getAsJsonObject();
        String upperCodeId = item.get("upperCodeId").getAsString();

        if (isGEcode(upperCodeId)) {
            list.add(
                    GECodeCommand.builder()
                            .codeId(item.get("codeId").getAsString())
                            .codeNm(item.get("codeNm").getAsString())
                            .upperCodeId(item.get("upperCodeId").getAsString())
                            .upperCodeNm(item.get("upperCodeNm").getAsString())
                            .build()
            );
        }
    }

    private boolean isGEcode(String upperCodeId) {
        return Arrays.asList(Constants.GECODES).contains(upperCodeId);
    }

}
