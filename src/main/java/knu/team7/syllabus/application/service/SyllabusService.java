package knu.team7.syllabus.application.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.core.util.ApiUtil;
import knu.team7.syllabus.core.util.GsonUtil;
import knu.team7.syllabus.application.usecase.SyllabusUseCase;
import knu.team7.syllabus.domain.model.TempSchedule;
import knu.team7.syllabus.domain.model.TempSyllabus;
import knu.team7.syllabus.infrastructure.adapter.dto.out.Search;
import knu.team7.syllabus.infrastructure.adapter.dto.out.SearchPayloadCommand;
import knu.team7.syllabus.infrastructure.adapter.dto.out.SearchSyllabusCommand;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class SyllabusService implements SyllabusUseCase {
    @Override
    public TempSyllabus getSyllabus(String year, String season, String subjectCode) throws Exception {
        Search search = SearchSyllabusCommand.builder()
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
    public List<TempSchedule> getSchedule(String year, String season, String subjectCode) throws Exception {
        Search search = SearchSyllabusCommand.builder()
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
                GsonUtil.toJson(SearchPayloadCommand.builder()
                        .search(search)
                        .build()),
                null);
    }

    private TempSyllabus parsingSyllabusData(String jsonData) {
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonObject dataObject = GsonUtil.getAsJsonObject(jsonObject, "data");
        return syllabusDataExtraction(dataObject);

    }

    private List<TempSchedule> parsingScheduleData(String jsonData) {
        List<TempSchedule> codes = new ArrayList<>();
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonArray codesArray = GsonUtil.getAsJsonArray(jsonObject, "data");
        for (JsonElement jsonElement : codesArray) {
            JsonObject item = jsonElement.getAsJsonObject();
            TempSchedule schedule = scheduleDataExtraction(item);
            codes.add(schedule);
        }
        return codes;
    }

    private TempSchedule scheduleDataExtraction(JsonObject item) {
        return TempSchedule.builder()
                .oriLssnsGoalLrnngCntns(GsonUtil.getStringOrNull(item, "oriLssnsGoalLrnngCntns"))
                .oriLssnsMethdMediaCntns(GsonUtil.getStringOrNull(item, "oriLssnsMethdMediaCntns"))
                .oriTaskRsrchQstnCntns(GsonUtil.getStringOrNull(item, "oriTaskRsrchQstnCntns"))
                .weekSn(GsonUtil.getStringOrNull(item, "weekSn"))
                .oriRmrk(GsonUtil.getStringOrNull(item, "oriRmrk"))
                .build();
    }

    private TempSyllabus syllabusDataExtraction(JsonObject item) {
        return TempSyllabus.builder()
                .crseNo(GsonUtil.getStringOrNull(item, "crseNo"))
                .estblYear(GsonUtil.getStringOrNull(item, "estblYear"))
                .estblDprtnNm(GsonUtil.getStringOrNull(item, "estblDprtnNm"))
                .estblSmstrSctnm(GsonUtil.getStringOrNull(item, "estblSmstrSctnm"))
                .sbjetSctnm(GsonUtil.getStringOrNull(item, "sbjetSctnm"))
                .sbjetSctcd(GsonUtil.getStringOrNull(item, "sbjetSctcd"))
                .sbjetNm(GsonUtil.getStringOrNull(item, "sbjetNm"))
                .sbjetDvnno(GsonUtil.getStringOrNull(item, "sbjetDvnno"))
                .sbjetCd(GsonUtil.getStringOrNull(item, "sbjetCd"))
                .crditSyste(GsonUtil.getStringOrNull(item, "crditSyste"))
                .lssnsTimeInfo(GsonUtil.getStringOrNull(item, "lssnsTimeInfo"))
                .lctrmCd(GsonUtil.getStringOrNull(item, "lctrmCd"))
                .rprsnStfnoNm(GsonUtil.getStringOrNull(item, "rprsnStfnoNm"))
                .oriCntacMtlno(GsonUtil.getStringOrNull(item, "oriCntacMtlno"))
                .oriCntacEmail(GsonUtil.getStringOrNull(item, "oriCntacEmail"))
                .oriLctreGoalCntns(GsonUtil.getStringOrNull(item, "oriLctreGoalCntns"))
                .oriLctreGoalCntnsCom(GsonUtil.getStringOrNull(item, "oriLctreGoalCntnsCom"))
                .oriSmmryPrpsCntns(GsonUtil.getStringOrNull(item, "oriSmmryPrpsCntns"))
                .oriTchmtRefLtrtuCntns(GsonUtil.getStringOrNull(item, "oriTchmtRefLtrtuCntns"))
                .oriTaskEvltnMethdCntns(GsonUtil.getStringOrNull(item, "oriTaskEvltnMethdCntns"))
                .oriRcmmdPlrSbjetInfo(GsonUtil.getStringOrNull(item, "oriRcmmdPlrSbjetInfo"))
                .oriRcmmdSbstsSbjetInfo(GsonUtil.getStringOrNull(item, "oriRcmmdSbstsSbjetInfo"))
                .oriIntviTimeInfo(GsonUtil.getStringOrNull(item, "oriIntviTimeInfo"))
                .oriEvltnRate1(GsonUtil.getStringOrNull(item, "oriEvltnRate1"))
                .oriEvltnRate2(GsonUtil.getStringOrNull(item, "oriEvltnRate2"))
                .oriEvltnRate3(GsonUtil.getStringOrNull(item, "oriEvltnRate3"))
                .oriEvltnRate4(GsonUtil.getStringOrNull(item, "oriEvltnRate4"))
                .oriEvltnRate5(GsonUtil.getStringOrNull(item, "oriEvltnRate5"))
                .oriEvltnRate6(GsonUtil.getStringOrNull(item, "oriEvltnRate6"))
                .oriEvltnRate8(GsonUtil.getStringOrNull(item, "oriEvltnRate8"))
                .oriEvltnRate9(GsonUtil.getStringOrNull(item, "oriEvltnRate9"))
                .oriGradsTotalScre(GsonUtil.getStringOrNull(item, "oriGradsTotalScre"))
                .oriAttlcRefMtterCntns(GsonUtil.getStringOrNull(item, "oriAttlcRefMtterCntns"))
                .build();
    }
}
