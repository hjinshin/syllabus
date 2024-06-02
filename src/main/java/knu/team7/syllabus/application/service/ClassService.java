package knu.team7.syllabus.application.service;

import com.google.gson.*;
import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.core.util.ApiUtil;
import knu.team7.syllabus.core.util.GsonUtil;
import knu.team7.syllabus.application.port.in.command.CodeCommand;
import knu.team7.syllabus.application.usecase.ClassUseCase;
import knu.team7.syllabus.domain.model.TempLecture;
import knu.team7.syllabus.infrastructure.adapter.dto.out.Search;
import knu.team7.syllabus.infrastructure.adapter.dto.out.SearchClassCommand;
import knu.team7.syllabus.infrastructure.adapter.dto.out.SearchPayloadCommand;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ClassService implements ClassUseCase {

    @Override
    public List<TempLecture> getClassList(List<CodeCommand> codeCommandList, String year, String season) throws Exception {
        List<TempLecture> list = new ArrayList<>();
        for (CodeCommand item : codeCommandList) {
            Search search = getGEClass(item.codeId(), year, season);
            String response = requestClass(search);
            list.addAll(parsingData(response));
        }
        return list;
    }

    @Override
    public List<TempLecture> getClassList(String[] codeList, String year, String season) throws Exception {
        List<TempLecture> list = new ArrayList<>();
        for (String code : codeList) {
            Search search = getOtherClass(code, year, season);
            String response = requestClass(search);
            list.addAll(parsingData(response));
        }
        return list;
    }
    @Override
    public Search getGEClass(String code, String year, String season) {
        return SearchClassCommand.builder()
                .estblYear(year)
                .estblSmstrSctcd(season)
                .sbjetRelmCd(code)
                .gubun("01")
                .isApi("Y")
                .build();
    }

    @Override
    public Search getOtherClass(String code, String year, String season) {
        return SearchClassCommand.builder()
                .estblYear(year)
                .estblSmstrSctcd(season)
                .sbjetSctcd2(code)
                .gubun("01")
                .isApi("Y")
                .build();
    }


    private String requestClass(Search search) throws Exception {
            return ApiUtil.post(
                    Constants.CLASS_URL,
                GsonUtil.toJson(SearchPayloadCommand.builder()
                        .search(search)
                        .build()),
                null);
    }

    private List<TempLecture> parsingData(String jsonData) {
        List<TempLecture> codes = new ArrayList<>();
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonArray codesArray = GsonUtil.getAsJsonArray(jsonObject, "data");
        for (JsonElement jsonElement : codesArray) {
            JsonObject item = jsonElement.getAsJsonObject();
            TempLecture tempLecture = classDataExtraction(item);
            codes.add(tempLecture);
        }

        return codes;
    }

    private TempLecture classDataExtraction(JsonObject item) {
        return TempLecture.builder()
                .estblYear(GsonUtil.getStringOrNull(item, "estblYear"))
                .estblSmstrSctnm(GsonUtil.getStringOrNull(item, "estblSmstrSctnm"))
                .estblSmstrSctcd(GsonUtil.getStringOrNull(item, "estblSmstrSctcd"))
                .sbjetSctnm(GsonUtil.getStringOrNull(item, "sbjetSctnm"))
                .estblUnivNm(GsonUtil.getStringOrNull(item, "estblUnivNm"))
                .estblDprtnNm(GsonUtil.getStringOrNull(item, "estblDprtnNm"))
                .estblGrade(GsonUtil.getStringOrNull(item, "estblGrade"))
                .sbjetNm(GsonUtil.getStringOrNull(item, "sbjetNm"))
                .sbjetCd(GsonUtil.getStringOrNull(item, "sbjetCd"))
                .crseNo(GsonUtil.getStringOrNull(item, "crseNo"))
                .lssnsTimeInfo(GsonUtil.getStringOrNull(item, "lssnsTimeInfo"))
                .lssnsRealTimeInfo(GsonUtil.getStringOrNull(item, "lssnsRealTimeInfo"))
                .crdit(GsonUtil.getStringOrNull(item, "crdit"))
                .thryTime(GsonUtil.getStringOrNull(item, "thryTime"))
                .prctsTime(GsonUtil.getStringOrNull(item, "prctsTime"))
                .totalPrfssNm(GsonUtil.getStringOrNull(item, "totalPrfssNm"))
                .lctrmInfo(GsonUtil.getStringOrNull(item, "lctrmInfo"))
                .rmnmCd(GsonUtil.getStringOrNull(item, "rmnmCd"))
                .attlcPrscpCnt(GsonUtil.getStringOrNull(item, "attlcPrscpCnt"))
                .doPlan(GsonUtil.getStringOrNull(item, "doPlan"))
                .expniSllbsYn(GsonUtil.getStringOrNull(item, "expniSllbsYn"))
                .rmrk(GsonUtil.getStringOrNull(item, "rmrk"))
                .build();
    }
}
