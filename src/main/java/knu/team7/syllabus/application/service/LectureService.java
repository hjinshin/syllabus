package knu.team7.syllabus.application.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import knu.team7.syllabus.application.port.in.command.ListCommand;
import knu.team7.syllabus.application.port.out.command.OutLectureCommand;
import knu.team7.syllabus.application.usecase.LectureUseCase;
import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.core.util.ApiUtil;
import knu.team7.syllabus.core.util.GsonUtil;
import knu.team7.syllabus.infrastructure.adapter.dto.out.Search;
import knu.team7.syllabus.infrastructure.adapter.dto.out.SearchClassCommand;
import knu.team7.syllabus.infrastructure.adapter.dto.out.SearchPayloadCommand;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class LectureService implements LectureUseCase {

    @Override
    public List<OutLectureCommand> getGELectureList(List<ListCommand> idList, String year, String season) throws Exception {
        List<OutLectureCommand> list = new ArrayList<>();
        for (ListCommand command : idList) {
            String code = command.sCodeId() == null ? command.mCodeId() : command.sCodeId();
            Search search = getGELecture(code, year, season);
            String response = requestLecture(search);
            list.addAll(parsingData(response, code));
        }
        return list;
    }

    @Override
    public List<OutLectureCommand> getOtherLectureList(List<ListCommand> idList, String year, String season) throws Exception {
        List<OutLectureCommand> list = new ArrayList<>();
        for (ListCommand command : idList) {
            String code = getCode(command);
            Search search = getOtherLecture(code, year, season);
            String response = requestLecture(search);
            list.addAll(parsingData(response, code));
        }
        return list;
    }

    public Search getGELecture(String code, String year, String season) {
        return SearchClassCommand.builder()
                .estblYear(year)
                .estblSmstrSctcd(season)
                .sbjetRelmCd(code)
                .gubun("01")
                .isApi("Y")
                .build();
    }

    public Search getOtherLecture(String code, String year, String season) {
        return SearchClassCommand.builder()
                .estblYear(year)
                .estblSmstrSctcd(season)
                .sbjetSctcd2(code)
                .gubun("01")
                .isApi("Y")
                .build();
    }


    private String requestLecture(Search search) throws Exception {
            return ApiUtil.post(
                    Constants.CLASS_URL,
                GsonUtil.toJson(SearchPayloadCommand.builder()
                        .search(search)
                        .build()),
                null);
    }

    private List<OutLectureCommand> parsingData(String jsonData, String codeId) {
        List<OutLectureCommand> codes = new ArrayList<>();
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonArray codesArray = GsonUtil.getAsJsonArray(jsonObject, "data");
        for (JsonElement jsonElement : codesArray) {
            JsonObject item = jsonElement.getAsJsonObject();
            OutLectureCommand outLectureCommand = classDataExtraction(item, codeId);
            codes.add(outLectureCommand);
        }

        return codes;
    }

    private OutLectureCommand classDataExtraction(JsonObject item, String codeId) {
        return OutLectureCommand.builder()
                .codeId(codeId)
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
                .totalPrfssNm(Optional.ofNullable(GsonUtil.getStringOrNull(item, "totalPrfssNm"))
                        .map(name -> name.replaceAll("<br/>", ", "))
                        .orElse(null))
                .lctrmInfo(GsonUtil.getStringOrNull(item, "lctrmInfo"))
                .rmnmCd(GsonUtil.getStringOrNull(item, "rmnmCd"))
                .attlcPrscpCnt(GsonUtil.getStringOrNull(item, "attlcPrscpCnt"))
                .doPlan(GsonUtil.getStringOrNull(item, "doPlan"))
                .expniSllbsYn(GsonUtil.getStringOrNull(item, "expniSllbsYn"))
                .rmrk(GsonUtil.getStringOrNull(item, "rmrk"))
                .build();
    }

    private String getCode(ListCommand item) {
        if (item.sCodeId() != null) {
            return item.sCodeId();
        }
        if (item.mCodeId() != null) {
            return item.mCodeId();
        }
        return item.lCodeId();
    }
}
