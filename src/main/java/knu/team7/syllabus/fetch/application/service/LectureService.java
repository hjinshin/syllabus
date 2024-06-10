package knu.team7.syllabus.fetch.application.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import knu.team7.syllabus.fetch.application.port.in.command.ListCommand;
import knu.team7.syllabus.fetch.application.port.out.command.OutLectureCommand;
import knu.team7.syllabus.fetch.application.usecase.LectureUseCase;
import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.core.type.DayType;
import knu.team7.syllabus.core.util.ApiUtil;
import knu.team7.syllabus.core.util.GsonUtil;
import knu.team7.syllabus.fetch.domain.model.LectureTime;
import knu.team7.syllabus.fetch.application.service.dto.out.Search;
import knu.team7.syllabus.fetch.application.service.dto.out.SearchClassCommand;
import knu.team7.syllabus.fetch.application.service.dto.out.SearchPayloadCommand;
import lombok.RequiredArgsConstructor;

import java.util.*;

@UseCase
@RequiredArgsConstructor
public class LectureService implements LectureUseCase {
    private final Map<Character, DayType> dayTypeMap = new HashMap<>() {{
            put('월', DayType.MON); put('화', DayType.TUE); put('수', DayType.WED); put('목', DayType.THU); put('금', DayType.FRI); put('토', DayType.SAT); put('일', DayType.SUN);
        }};

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
        if (codesArray == null) {
            return codes;
        }
        for (JsonElement jsonElement : codesArray) {
            JsonObject item = jsonElement.getAsJsonObject();
            OutLectureCommand outLectureCommand = classDataExtraction(item, codeId);
            codes.add(outLectureCommand);
        }

        return codes;
    }

    private OutLectureCommand classDataExtraction(JsonObject item, String codeId) {
        String rmrk = GsonUtil.getStringOrNull(item, "rmrk");
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
                .humanities(isHumanities(rmrk))
                .sdg(isSDG(rmrk))
                .flipped(isFlipped(rmrk))
                .nU((isNU(rmrk)))
                .dgKp(isDGKP(rmrk))
                .su(isSU(rmrk))
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

    private List<LectureTime> parseLssnsTimeInfoToList(String lssnsTimeInfo) {
        List<LectureTime> lectureTimeList = new ArrayList<>();
        if (lssnsTimeInfo == null) {
            return lectureTimeList;
        }
        lssnsTimeInfo = lssnsTimeInfo.replaceAll("\\s", "");
        String[] result = lssnsTimeInfo.split("(?=월|화|수|목|금)");

        for (String s : result) {
            DayType day = dayTypeMap.get(s.charAt(0));
            String codeStr = s.substring(1);
            String[] codes = codeStr.split(",");
            for (String code : codes) {
                lectureTimeList.add(createLectureTime(day, code));
            }
        }
        return lectureTimeList;
    }

    LectureTime createLectureTime(DayType day, String code) {
        return LectureTime.builder()
                .day(day)
                .timeCode(code)
                .build();
    }

    private boolean isHumanities(String rmrk) {
        if (rmrk == null) {
            return false;
        }
        return rmrk.contains("인문교양");
    }
    private boolean isSDG(String rmrk) {
        if (rmrk == null) {
            return false;
        }
        return rmrk.contains("SDG") || rmrk.contains("지속가능발전목표");
    }
    private boolean isFlipped(String rmrk) {
        if (rmrk == null) {
            return false;
        }
        return rmrk.contains("플립드러닝강좌");
    }
    private boolean isNU(String rmrk) {
        if (rmrk == null) {
            return false;
        }
        return rmrk.contains("거점국립대원격강좌");
    }
    private boolean isDGKP(String rmrk) {
        if (rmrk == null) {
            return false;
        }
        return rmrk.contains("대구경북권역원격강좌");
    }
    private boolean isSU(String rmrk) {
        if (rmrk == null) {
            return false;
        }
        return rmrk.contains("SU평가강좌");
    }

}
