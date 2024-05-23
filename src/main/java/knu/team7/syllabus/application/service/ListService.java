package knu.team7.syllabus.application.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import knu.team7.core.Constants;
import knu.team7.core.annotation.UseCase;
import knu.team7.core.util.ApiUtil;
import knu.team7.core.util.GsonUtil;
import knu.team7.syllabus.application.port.in.command.CodeCommand;
import knu.team7.syllabus.application.usecase.ListUseCase;
import knu.team7.syllabus.infrastructure.adapter.dto.out.Search;
import knu.team7.syllabus.infrastructure.adapter.dto.out.SearchListCommand;
import knu.team7.syllabus.infrastructure.adapter.dto.out.SearchPayloadCommand;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListService implements ListUseCase {
    @Override
    public List<CodeCommand> getGEList() throws Exception {
        List<CodeCommand> list = new ArrayList<>();
        for (String gelist_key : Constants.GELIST_KEYS) {
            Search search = SearchListCommand.builder()
                    .key(gelist_key)
                    .build();
            String response = requestList(search);
            list.addAll(parsingData(response));
        }
        return list;
    }

    @Override
    public List<CodeCommand> getSubjectList() throws Exception {
        Search search = SearchListCommand.builder()
                .key(Constants.SUBLIST_KEY)
                .build();
        String response = requestList(search);
        System.out.println(response);
        return new ArrayList<>(parsingData(response));
    }

    private String requestList(Search search) throws Exception {
        return ApiUtil.post(
                Constants.LIST_URL,
                GsonUtil.toJson(SearchPayloadCommand.builder()
                        .search(search)
                        .build()),
                null);
    }

    private List<CodeCommand> parsingData(String jsonData) {
        List<CodeCommand> codes = new ArrayList<>();
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonObject dataObject = GsonUtil.getAsJsonObject(jsonObject, "data");
        JsonObject optionObject = GsonUtil.getAsJsonObject(dataObject, "option");
        JsonArray codesArray = GsonUtil.getAsJsonArray(optionObject, "codes");
        for (JsonElement jsonElement : codesArray) {
            addItem(codes, jsonElement);
        }
        return codes;
    }

    private void addItem(List<CodeCommand> list, JsonElement jsonElement) {
        JsonObject item = jsonElement.getAsJsonObject();
        String upperCodeId = getAsString(item, "upperCodeId");

        if (isGEcode(upperCodeId)) {
            list.add(
                    CodeCommand.builder()
                        .codeId(getAsString(item, "codeId"))
                        .codeNm(getAsString(item, "codeNm"))
                        .upperCodeId(getAsString(item, "upperCodeId"))
                        .upperCodeNm(getAsString(item, "upperCodeNm"))
                        .build()
            );
        }
    }

    private String getAsString(JsonObject jsonObject, String key) {
         JsonElement jsonElement = jsonObject.get(key);
        if (jsonElement != null && !jsonElement.isJsonNull()) {
            return jsonElement.getAsString();
        }
        return null;
    }

    private boolean isGEcode(String upperCodeId) {
        return Arrays.asList(Constants.GECODES).contains(upperCodeId);
    }

}
