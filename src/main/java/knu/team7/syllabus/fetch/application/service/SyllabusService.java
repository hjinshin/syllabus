package knu.team7.syllabus.fetch.application.service;

import com.google.gson.JsonObject;
import knu.team7.syllabus.fetch.application.usecase.SyllabusUseCase;
import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.core.util.ApiUtil;
import knu.team7.syllabus.core.util.GsonUtil;
import knu.team7.syllabus.fetch.domain.model.Course;
import knu.team7.syllabus.fetch.domain.model.Syllabus;
import knu.team7.syllabus.fetch.application.service.dto.out.Search;
import knu.team7.syllabus.fetch.application.service.dto.out.SearchPayloadCommand;
import knu.team7.syllabus.fetch.application.service.dto.out.SearchSyllabusCommand;
import knu.team7.syllabus.fetch.application.service.dto.in.SyllabusParamsCommand;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class SyllabusService implements SyllabusUseCase {
    @Override
    public List<Syllabus> getSyllabusList(List<SyllabusParamsCommand> commandList, List<Course> courseList) throws Exception {
        List<Syllabus> list = new ArrayList<>();
        for (int i = 0; i < commandList.size(); i++) {
            list.add(getSyllabus(commandList.get(i).year(), commandList.get(i).season(),
                    commandList.get(i).crseNo(), courseList.get(i), "Kor"));
            list.add(getSyllabus(commandList.get(i).year(), commandList.get(i).season(),
                    commandList.get(i).crseNo(), courseList.get(i), "Eng"));
        }

        return list;
    }

    @Override
    public Syllabus getSyllabus(String year, String season, String subjectCode, Course course, String doPlan) throws Exception {
        String[] code = subjectCode.split("-");
        Search search = SearchSyllabusCommand.builder()
                .estblYear(year)
                .estblSmstrSctcd(season)
                .sbjetCd(code[0])
                .sbjetDvnno(code[1])
                .lctreLnggeSctcd(season.replace("CMBS", "STCU"))
                .isApi("Y")
                .doPlan(doPlan)
                .build();
        String response = requestSyllabus(search);
        return parsingSyllabusData(response, course, doPlan);
    }

    private String requestSyllabus(Search search) throws Exception {
        return ApiUtil.post(
                Constants.SYLLABUS_URL,
                GsonUtil.toJson(SearchPayloadCommand.builder()
                        .search(search)
                        .build()),
                null);
    }

    private Syllabus parsingSyllabusData(String jsonData, Course course, String doPlan) {
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonObject dataObject = GsonUtil.getAsJsonObject(jsonObject, "data");
        return syllabusDataExtraction(dataObject, course, doPlan);

    }
    private Syllabus syllabusDataExtraction(JsonObject item, Course course, String doPlan) {
        return Syllabus.builder()
                .crseNo(GsonUtil.getStringOrNull(item, "crseNo"))
                .crseGoal(GsonUtil.getStringOrNull(item, "oriLctreGoalCntns"))
                .eduGoal(GsonUtil.getStringOrNull(item, "oriLctreGoalCntnsCom"))
                .summary(GsonUtil.getStringOrNull(item, "oriSmmryPrpsCntns"))
                .textbook(GsonUtil.getStringOrNull(item, "oriTchmtRefLtrtuCntns"))
                .evalMethd(GsonUtil.getStringOrNull(item, "oriTaskEvltnMethdCntns"))
                .intviTimeLoc(GsonUtil.getStringOrNull(item, "oriIntviTimeInfo"))
                .refer(GsonUtil.getStringOrNull(item, "oriAttlcRefMtterCntns"))
                .lang(GsonUtil.getStringOrNull(item, "rprsnLctreLnggeSctnm"))

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
                .total(GsonUtil.nullTo0(GsonUtil.getStringOrNull(item, "oriGradsTotalScre")))
                .doPlan(doPlan)
                .course(course)
                .build();
    }
}
