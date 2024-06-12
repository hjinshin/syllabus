package knu.team7.syllabus.fetch.infrastructure.adapter.external;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.core.util.ApiUtil;
import knu.team7.syllabus.core.util.GsonUtil;
import knu.team7.syllabus.fetch.application.port.in.command.external.Search;
import knu.team7.syllabus.fetch.application.port.in.command.external.SearchListCommand;
import knu.team7.syllabus.fetch.application.port.in.command.external.SearchPayloadCommand;
import knu.team7.syllabus.fetch.application.port.out.FetchCategoryPort;
import knu.team7.syllabus.fetch.domain.model.Category;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ExternalCategoryAdapter implements FetchCategoryPort {
    @Override
    public List<Category> fetchGECategory(String key) throws Exception {
        Search search = SearchListCommand.builder()
                .key(key)
                .build();
        String response = requestList(search);
        return parsingGEData(response);
    }
    private String requestList(Search search) throws Exception {
        return ApiUtil.post(
                Constants.LIST_URL,
                GsonUtil.toJson(SearchPayloadCommand.builder()
                        .search(search)
                        .build()),
                null);
    }

    private List<Category> parsingGEData(String jsonData) {
        List<Category> codes = new ArrayList<>();
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonObject dataObject = GsonUtil.getAsJsonObject(jsonObject, "data");
        JsonObject optionObject = GsonUtil.getAsJsonObject(dataObject, "option");
        JsonArray codesArray = GsonUtil.getAsJsonArray(optionObject, "codes");
        for (JsonElement jsonElement : codesArray) {
            addGEItem(codes, jsonElement);
        }
        return codes;
    }

    private void addGEItem(List<Category> list, JsonElement jsonElement) {
        JsonObject item = jsonElement.getAsJsonObject();
        String upperCodeId = GsonUtil.getAsString(item, "upperCodeId");

        if (isGEcode(upperCodeId)) {
            list.add(
                    Category.builder()
                            .category("교양")
                            .mCodeId(GsonUtil.getAsString(item, "codeId"))
                            .mCodeNm(GsonUtil.getAsString(item, "codeNm"))
                            .lCodeId(GsonUtil.getAsString(item, "upperCodeId"))
                            .lCodeNm(GsonUtil.getAsString(item, "upperCodeNm"))
                            .build()
            );
        }
        if (isCoreGE(upperCodeId)) {
            list.add(
                    Category.builder()
                            .category("교양")
                            .sCodeId(GsonUtil.getAsString(item, "codeId"))
                            .sCodeNm(GsonUtil.getAsString(item, "codeNm"))
                            .mCodeId(GsonUtil.getAsString(item, "upperCodeId"))
                            .mCodeNm(GsonUtil.getAsString(item, "upperCodeNm"))
                            .lCodeId(Constants.CHUMSUNGINCORE[0])
                            .lCodeNm(Constants.CHUMSUNGINCORE[1])
                            .build()
            );
        }
    }

    private boolean isGEcode(String upperCodeId) {
        return Arrays.stream(Constants.GECATEGORYCODES)
                .anyMatch(element -> element[0].equals(upperCodeId));
    }

    private boolean isCoreGE(String upperCodeId) {
        return Arrays.stream(Constants.GECATEGORYCORECODES)
                .anyMatch(element -> element[0].equals(upperCodeId));
    }
}
