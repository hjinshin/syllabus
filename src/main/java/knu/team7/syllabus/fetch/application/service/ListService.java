package knu.team7.syllabus.fetch.application.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import knu.team7.syllabus.fetch.application.port.in.command.ListCommand;
import knu.team7.syllabus.fetch.application.usecase.ListUseCase;
import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.core.util.ApiUtil;
import knu.team7.syllabus.core.util.GsonUtil;
import knu.team7.syllabus.fetch.application.service.dto.out.Search;
import knu.team7.syllabus.fetch.application.service.dto.out.SearchListCommand;
import knu.team7.syllabus.fetch.application.service.dto.out.SearchPayloadCommand;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListService implements ListUseCase {
    @Override
    public List<ListCommand> getGEList() throws Exception {
        List<ListCommand> list = new ArrayList<>();
        for (String gelist_key : Constants.GELIST_KEYS) {
            Search search = SearchListCommand.builder()
                    .key(gelist_key)
                    .build();
            String response = requestList(search);
            list.addAll(parsingGEData(response));
        }
        return list;
    }

    @Override
    public List<ListCommand> getOtherList() {
        List<ListCommand> list = new ArrayList<>();
        for (String[] codes : Constants.SUBCODES) {

            list.add(ListCommand.builder()
                    .section(codes[1])
                    .lCodeId(codes[0])
                    .build()
            );
        }

        return list;
    }

    private String requestList(Search search) throws Exception {
        return ApiUtil.post(
                Constants.LIST_URL,
                GsonUtil.toJson(SearchPayloadCommand.builder()
                        .search(search)
                        .build()),
                null);
    }

    private List<ListCommand> parsingGEData(String jsonData) {
        List<ListCommand> codes = new ArrayList<>();
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonObject dataObject = GsonUtil.getAsJsonObject(jsonObject, "data");
        JsonObject optionObject = GsonUtil.getAsJsonObject(dataObject, "option");
        JsonArray codesArray = GsonUtil.getAsJsonArray(optionObject, "codes");
        for (JsonElement jsonElement : codesArray) {
            addGEItem(codes, jsonElement);
        }
        return codes;
    }

    private void addGEItem(List<ListCommand> list, JsonElement jsonElement) {
        JsonObject item = jsonElement.getAsJsonObject();
        String upperCodeId = getAsString(item, "upperCodeId");

        if (isGEcode(upperCodeId)) {
            list.add(
                    ListCommand.builder()
                            .section("교양")
                            .mCodeId(getAsString(item, "codeId"))
                            .mCodeNm(getAsString(item, "codeNm"))
                            .lCodeId(getAsString(item, "upperCodeId"))
                            .lCodeNm(getAsString(item, "upperCodeNm"))
                            .build()
            );
        }
        if (isCoreGE(upperCodeId)) {
            list.add(
                    ListCommand.builder()
                            .section("교양")
                            .sCodeId(getAsString(item, "codeId"))
                            .sCodeNm(getAsString(item, "codeNm"))
                            .mCodeId(getAsString(item, "upperCodeId"))
                            .mCodeNm(getAsString(item, "upperCodeNm"))
                            .lCodeId(Constants.CHUMSUNGINCORE[0])
                            .lCodeNm(Constants.CHUMSUNGINCORE[1])
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
        return Arrays.stream(Constants.GECODES)
                .anyMatch(element -> element[0].equals(upperCodeId));
    }

    private boolean isCoreGE(String upperCodeId) {
        return Arrays.stream(Constants.GECORECODES)
                .anyMatch(element -> element[0].equals(upperCodeId));
    }

}
