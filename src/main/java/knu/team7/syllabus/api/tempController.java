package knu.team7.syllabus.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import knu.team7.syllabus.application.port.in.command.ListCommand;
import knu.team7.syllabus.application.port.out.command.OutLectureCommand;
import knu.team7.syllabus.application.usecase.ScheduleUseCase;
import knu.team7.syllabus.application.port.out.command.OutScheduleCommand;
import knu.team7.syllabus.application.usecase.LectureUseCase;
import knu.team7.syllabus.application.usecase.ListUseCase;
import knu.team7.syllabus.application.usecase.SyllabusUseCase;
import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.util.ApiUtil;
import knu.team7.syllabus.core.util.GsonUtil;
import knu.team7.syllabus.domain.model.Syllabus;
import knu.team7.syllabus.infrastructure.adapter.dto.out.SearchPayloadCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class tempController {
    private final ListUseCase listUseCase;
    private final LectureUseCase lectureUseCase;
    private final SyllabusUseCase syllabusUseCase;
    private final ScheduleUseCase scheduleUseCase;
    @GetMapping("/ge/list")
    public ResponseEntity<List<ListCommand>> tempGetGEList() {
        try {
            List<ListCommand> geList = listUseCase.getGEList();
            return ResponseEntity.ok(geList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    @GetMapping("/ge")
    public ResponseEntity<List<OutLectureCommand>> tempGetGEClassList() {
        try {
            String year = "2024";
            String season = Constants.SEASONCODES.get("1학기");
            List<ListCommand> geList = listUseCase.getGEList();
            List<OutLectureCommand> classes = lectureUseCase.getGELectureList(geList, year, season);
            return ResponseEntity.ok(classes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ArrayList<>());
        }
    }
    @GetMapping("/other")
    public ResponseEntity<List<OutLectureCommand>> tempGetOtherCLassList() {
        try {
            String year = "2024";
            String season = Constants.SEASONCODES.get("1학기");
            List<ListCommand> otherList = listUseCase.getOtherList();

            List<OutLectureCommand> classes = lectureUseCase.getOtherLectureList(otherList, year, season);
            return ResponseEntity.ok(classes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    @GetMapping("/syllabus")
    public ResponseEntity<Syllabus> tempGetSyllabus() {
        try {
            String year = "2024";
            String season = Constants.SEASONCODES.get("1학기");
            String subjectCode = "CLTR0090-001"; // 현대사회와법
            Syllabus syllabus = syllabusUseCase.getSyllabus(year, season, subjectCode);
            return ResponseEntity.ok(syllabus);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(Syllabus.builder().build());
        }
    }

    @GetMapping("/syllabus/schedule")
    public ResponseEntity<List<OutScheduleCommand>> tempGetSyllabusSchedule() {
        try {
            String year = "2024";
            String season = Constants.SEASONCODES.get("1학기");
            String subjectCode = "CLTR0090-001"; // 현대사회와법
            List<OutScheduleCommand> outScheduleCommand = scheduleUseCase.getSchedule(year, season, subjectCode);
            return ResponseEntity.ok(outScheduleCommand);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    @GetMapping("/building/list")
    public ResponseEntity<List<String>> tempGetBuildingList() {
        try {
            String res = ApiUtil.post(
                    "https://knuin.knu.ac.kr/public/web/stddm/common/bldngLctrm/selectBldngBldngLctrm",
                    GsonUtil.toJson(SearchPayloadCommand.builder().build()),
                    null
            );
            List<String> list = new ArrayList<>();

            JsonObject jsonObject = GsonUtil.fromJson(res);
            JsonArray codesArray = GsonUtil.getAsJsonArray(jsonObject, "data");
            for (JsonElement jsonElement : codesArray) {
                JsonObject item = jsonElement.getAsJsonObject();
                String temp = GsonUtil.getStringOrNull(item, "bldngNm");
                if (!temp.contains("철거")) {
                    temp = temp.replaceAll("\\(.*?\\)", "");
                    char x = temp.charAt(0);
                    if (x== ' ') {
                        temp = temp.substring((1));
                    }
                    //temp = "경북대학교" + temp;
                    list.add(temp);
                }
            }

            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }
}
