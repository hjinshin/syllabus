package knu.team7.syllabus.core.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.type.DayType;
import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;
import knu.team7.syllabus.fetch.application.port.in.command.YearAndSeasonCommand;
import knu.team7.syllabus.fetch.domain.model.LectureTime;
import knu.team7.syllabus.fetch.domain.model.Category;
import knu.team7.syllabus.fetch.application.port.in.command.SyllabusCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParseUtil {

    public static YearAndSeasonCommand parsingYearSeasonData(String jsonData) {
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonObject dataObject = GsonUtil.getAsJsonObject(jsonObject, "data");
        String year = GsonUtil.getStringOrNull(dataObject, "year");
        String seasonCode = GsonUtil.getStringOrNull(dataObject, "smstrSctcd");
        return YearAndSeasonCommand.builder()
                .year(Integer.parseInt(nullTo0(year)))
                .season(Constants.SEASONNAMES.get(seasonCode))
                .build();
    }
    public static List<LectureCommand> parsingLectureData(String jsonData, String sectCd) {
        List<LectureCommand> codes = new ArrayList<>();
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonArray codesArray = GsonUtil.getAsJsonArray(jsonObject, "data");
        if (codesArray == null) {
            return codes;
        }
        for (JsonElement jsonElement : codesArray) {
            JsonObject item = jsonElement.getAsJsonObject();
            LectureCommand lectureCommand = lectureDataExtraction(item, sectCd);
            codes.add(lectureCommand);
        }

        return codes;
    }
    public static SyllabusCommand parsingSyllabusData(String jsonData, String doPlan) {
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonObject dataObject = GsonUtil.getAsJsonObject(jsonObject, "data");
        return syllabusDataExtraction(dataObject, doPlan);

    }

    private static LectureCommand lectureDataExtraction(JsonObject item, String sectCd) {
        String rmrk = GsonUtil.getStringOrNull(item, "rmrk");
        return LectureCommand.builder()
                .sectCd(sectCd)
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
                .lssnsTimeInfo(
                        parseLssnsTimeInfoToList(GsonUtil.getStringOrNull(item, "lssnsTimeInfo")))
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
                .expniSllbsYn(GsonUtil.getStringOrNull(item, "expniSllbsYn"))
                .rmrk(rmrk)
                .humanities(StringChecker(rmrk, "인문교양"))
                .sdg(StringChecker(rmrk, "SDG") || StringChecker(rmrk, "지속가능발전목표"))
                .flipped(StringChecker(rmrk, "플립드러닝강좌"))
                .nU(StringChecker(rmrk, "거점국립대원격강좌"))
                .dgKp(StringChecker(rmrk, "대구경북권역원격강좌"))
                .su(StringChecker(rmrk, "SU평가강좌"))
                .build();
    }

    private static SyllabusCommand syllabusDataExtraction(JsonObject item, String doPlan) {
        return SyllabusCommand.builder()
                .crseNo(GsonUtil.getStringOrNull(item, "crseNo"))
                .year(GsonUtil.getStringOrNull(item, "estblYear"))
                .season(GsonUtil.getStringOrNull(item, "estblSmstrSctnm"))
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
                .total(nullTo0(GsonUtil.getStringOrNull(item, "oriGradsTotalScre")))
                .doPlan(doPlan)
                .build();
    }

    private static List<LectureTime> parseLssnsTimeInfoToList(String lssnsTimeInfo) {
        List<LectureTime> lectureTimeList = new ArrayList<>();
        if (lssnsTimeInfo == null) {
            return lectureTimeList;
        }
        lssnsTimeInfo = lssnsTimeInfo.replaceAll("\\s", "");
        String[] result = lssnsTimeInfo.split("(?=월|화|수|목|금)");

        for (String s : result) {
            DayType day = Constants.DAY_TYPE.get(s.charAt(0));
            String codeStr = s.substring(1);
            String[] codes = codeStr.split(",");
            for (String code : codes) {
                lectureTimeList.add(createLectureTime(day, code));
            }
        }
        return lectureTimeList;
    }

    static LectureTime createLectureTime(DayType day, String code) {
        return LectureTime.builder()
                .day(day)
                .timeCode(code)
                .build();
    }
    public static String getCode(Category item) {
        if (item.getSCodeId() != null) {
            return item.getSCodeId();
        }
        if (item.getMCodeId() != null) {
            return item.getMCodeId();
        }
        return item.getLCodeId();
    }
    public static boolean StringChecker(String str, String comparator) {
        if(str == null) return false;
        return str.contains(comparator);
    }


    public static String nullTo0(String item) {
        if (item == null) {
            return "0";
        }
        return item;
    }
}
