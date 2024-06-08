package knu.team7.syllabus.application.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import knu.team7.syllabus.application.usecase.ScheduleUseCase;
import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.core.util.ApiUtil;
import knu.team7.syllabus.core.util.GsonUtil;
import knu.team7.syllabus.application.port.out.command.OutScheduleCommand;
import knu.team7.syllabus.infrastructure.adapter.dto.out.Search;
import knu.team7.syllabus.infrastructure.adapter.dto.out.SearchPayloadCommand;
import knu.team7.syllabus.infrastructure.adapter.dto.out.SearchSyllabusCommand;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ScheduleService implements ScheduleUseCase {

    @Override
    public List<OutScheduleCommand> getSchedule(String year, String season, String subjectCode) throws Exception {
        String[] codes = subjectCode.split("-");
        Search search = SearchSyllabusCommand.builder()
                .estblYear(year)
                .estblSmstrSctcd(Constants.SEASONCODES.get(season))
                .sbjetCd(codes[0])
                .sbjetDvnno(codes[1])
                .lctreLnggeSctcd(Constants.SEASONCODES.get(season).replace("CMBS", "STCU"))
                .isApi("Y")
                .build();
        String response = requestSchedule(search);
        return parsingScheduleData(response);
    }

    private String requestSchedule(Search search) throws Exception {
        return ApiUtil.post(
                Constants.SCHEDULE_URL,
                GsonUtil.toJson(SearchPayloadCommand.builder()
                        .search(search)
                        .build()),
                null);
    }

    private List<OutScheduleCommand> parsingScheduleData(String jsonData) {
        List<OutScheduleCommand> codes = new ArrayList<>();
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonArray codesArray = GsonUtil.getAsJsonArray(jsonObject, "data");
        for (JsonElement jsonElement : codesArray) {
            JsonObject item = jsonElement.getAsJsonObject();
            OutScheduleCommand outScheduleCommand = scheduleDataExtraction(item);
            codes.add(outScheduleCommand);
        }
        return codes;
    }

    private OutScheduleCommand scheduleDataExtraction(JsonObject item) {
        return OutScheduleCommand.builder()
                .lssnsGoalCntns(GsonUtil.getStringOrNull(item, "oriLssnsGoalLrnngCntns"))
                .lssnsMethd(GsonUtil.getStringOrNull(item, "oriLssnsMethdMediaCntns"))
                .rsrchCntns(GsonUtil.getStringOrNull(item, "oriTaskRsrchQstnCntns"))
                .weekSn(GsonUtil.getStringOrNull(item, "weekSn"))
                .weekNote(GsonUtil.getStringOrNull(item, "oriRmrk"))
                .build();
    }
}
