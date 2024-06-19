package knu.team7.syllabus.fetch.infrastructure.adapter.external;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.core.util.ApiUtil;
import knu.team7.syllabus.core.util.GsonUtil;
import knu.team7.syllabus.fetch.application.port.in.command.ScheduleCommand;
import knu.team7.syllabus.fetch.application.port.in.command.external.Search;
import knu.team7.syllabus.fetch.application.port.in.command.external.SearchPayloadCommand;
import knu.team7.syllabus.fetch.application.port.in.command.external.SearchSyllabusCommand;
import knu.team7.syllabus.fetch.application.port.out.FetchSchedulePort;
import knu.team7.syllabus.fetch.domain.model.Course;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ExternalScheduleAdapter implements FetchSchedulePort {
    @Override
    public List<ScheduleCommand> fetchSchedule(Course course, String doPlan) throws Exception {
        String[] codes = course.getCrseNo().split("-");
        Search search = SearchSyllabusCommand.builder()
                .estblYear(String.valueOf(course.getYear()))
                .estblSmstrSctcd(Constants.SEASONCODES.get(course.getSeason()))
                .sbjetCd(codes[0])
                .sbjetDvnno(codes[1])
                .lctreLnggeSctcd(Constants.LCTRELNGGESCTCD_KEY)
                .isApi("Y")
                .doPlan(doPlan)
                .build();

        String response = requestSchedule(search);
        return parsingScheduleData(response, course, doPlan);
    }

    private String requestSchedule(Search search) throws Exception {
        return ApiUtil.post(
                Constants.SCHEDULE_URL,
                GsonUtil.toJson(SearchPayloadCommand.builder()
                        .search(search)
                        .build()),
                null);
    }

    private List<ScheduleCommand> parsingScheduleData(String jsonData, Course course, String doPlan) {
        List<ScheduleCommand> codes = new ArrayList<>();
        JsonObject jsonObject = GsonUtil.fromJson(jsonData);
        JsonArray codesArray = GsonUtil.getAsJsonArray(jsonObject, "data");
        if (codesArray == null) {
            return codes;
        }
        for (int i=0; i < codesArray.size(); i++) {
            JsonObject item = codesArray.get(i).getAsJsonObject();
            ScheduleCommand outScheduleCommand = scheduleDataExtraction(item, course, doPlan, i);
            codes.add(outScheduleCommand);
        }
        return codes;
    }

    private ScheduleCommand scheduleDataExtraction(JsonObject item, Course course, String doPlan, int no) {
        return ScheduleCommand.builder()
                .no(no+1)
                .lssnsGoalCntns(GsonUtil.getStringOrNull(item, "oriLssnsGoalLrnngCntns"))
                .lssnsMethd(GsonUtil.getStringOrNull(item, "oriLssnsMethdMediaCntns"))
                .rsrchCntns(GsonUtil.getStringOrNull(item, "oriTaskRsrchQstnCntns"))
                .weekSn(GsonUtil.getStringOrNull(item, "weekSn"))
                .weekNote(GsonUtil.getStringOrNull(item, "oriRmrk"))
                .course(course)
                .doPlan(doPlan)
                .build();
    }
}
