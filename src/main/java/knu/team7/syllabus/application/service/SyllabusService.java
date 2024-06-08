package knu.team7.syllabus.application.service;

import com.google.gson.JsonObject;
import knu.team7.syllabus.application.usecase.SyllabusUseCase;
import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.core.util.ApiUtil;
import knu.team7.syllabus.core.util.GsonUtil;
import knu.team7.syllabus.domain.model.Syllabus;
import knu.team7.syllabus.infrastructure.adapter.dto.in.SyllabusParamsCommand;
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
    public List<Syllabus> getSyllabusList(List<SyllabusParamsCommand> commandList) throws Exception {
        List<Syllabus> list = new ArrayList<>();
        for (SyllabusParamsCommand command : commandList) {
            list.add(getSyllabus(command.year(), command.season(), command.crseNo()));
        }
        return list;
    }

    @Override
    public Syllabus getSyllabus(String year, String season, String subjectCode) throws Exception {
        String[] code = subjectCode.split("-");
        Search search = SearchSyllabusCommand.builder()
                .estblYear(year)
                .estblSmstrSctcd(season)
                .sbjetCd(code[0])
                .sbjetDvnno(code[1])
                .lctreLnggeSctcd(season.replace("CMBS", "STCU"))
                .isApi("Y")
                .build();
        String response = requestSyllabus(search);
        return parsingSyllabusData(response);
    }

    private String requestSyllabus(Search search) throws Exception {
        return ApiUtil.post(
                Constants.SYLLABUS_URL,
                GsonUtil.toJson(SearchPayloadCommand.builder()
                        .search(search)
                        .build()),
                null);
    }

    private Syllabus parsingSyllabusData(String jsonData) {
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonObject dataObject = GsonUtil.getAsJsonObject(jsonObject, "data");
        return syllabusDataExtraction(dataObject);

    }
    private Syllabus syllabusDataExtraction(JsonObject item) {
        return Syllabus.builder()
                .crseNo(GsonUtil.getStringOrNull(item, "crseNo"))
                .crseGoal(GsonUtil.getStringOrNull(item, "oriLctreGoalCntns"))
                .eduGoal(GsonUtil.getStringOrNull(item, "oriLctreGoalCntnsCom"))
                .summary(GsonUtil.getStringOrNull(item, "oriSmmryPrpsCntns"))
                .textbook(GsonUtil.getStringOrNull(item, "oriTchmtRefLtrtuCntns"))
                .evalMethd(GsonUtil.getStringOrNull(item, "oriTaskEvltnMethdCntns"))
                .intviTimeLoc(GsonUtil.getStringOrNull(item, "oriIntviTimeInfo"))
                .refer(GsonUtil.getStringOrNull(item, "oriAttlcRefMtterCntns"))

                .profNm(GsonUtil.getStringOrNull(item, "rprsnStfnoNm"))
                .profTel(GsonUtil.getStringOrNull(item, "oriCntacMtlno"))
                .profEmail(GsonUtil.getStringOrNull(item, "oriCntacEmail"))

                .preSbjet(GsonUtil.getStringOrNull(item, "oriRcmmdPlrSbjetInfo"))
                .postSbjet(GsonUtil.getStringOrNull(item, "oriRcmmdSbstsSbjetInfo"))

                .attendance(GsonUtil.getStringOrNull(item, "oriEvltnRate1"))
                .midExam(GsonUtil.getStringOrNull(item, "oriEvltnRate2"))
                .finalExam(GsonUtil.getStringOrNull(item, "oriEvltnRate3"))
                .assignment(GsonUtil.getStringOrNull(item, "oriEvltnRate4"))
                .presentation(GsonUtil.getStringOrNull(item, "oriEvltnRate5"))
                .debate(GsonUtil.getStringOrNull(item, "oriEvltnRate6"))
                .safetyEdu(GsonUtil.getStringOrNull(item, "oriEvltnRate8"))
                .etc(GsonUtil.getStringOrNull(item, "oriEvltnRate9"))
                .total(GsonUtil.getStringOrNull(item, "oriGradsTotalScre"))
                .build();
    }
}
