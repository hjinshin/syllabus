package knu.team7.syllabus.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import knu.team7.syllabus.application.port.in.command.CodeCommand;
import knu.team7.syllabus.application.usecase.ClassUseCase;
import knu.team7.syllabus.application.usecase.ListUseCase;
import knu.team7.syllabus.application.usecase.SyllabusUseCase;
import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.util.ApiUtil;
import knu.team7.syllabus.core.util.GsonUtil;
import knu.team7.syllabus.domain.model.TempLecture;
import knu.team7.syllabus.domain.model.TempSchedule;
import knu.team7.syllabus.domain.model.TempSyllabus;
import knu.team7.syllabus.infrastructure.adapter.dto.out.SearchPayloadCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class tempController {

    private final ListUseCase listUseCase;
    private final ClassUseCase classUseCase;
    private final SyllabusUseCase syllabusUseCase;
    @GetMapping("/ge/list")
    public ResponseEntity<List<CodeCommand>> tempGetGEList() {
        try {
            List<CodeCommand> geList = listUseCase.getGEList();
            return ResponseEntity.ok(geList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    @GetMapping("/ge")
    public ResponseEntity<List<TempLecture>> tempGetGEClassList() {
        try {
            String year = "2024";
            String season = Constants.SEASONCODES[0][0]; // 1학기
            List<CodeCommand> geList = listUseCase.getGEList();
            List<TempLecture> classes = classUseCase.getClassList(geList, year, season);
            return ResponseEntity.ok(classes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ArrayList<>());
        }
    }
    @GetMapping("/other")
    public ResponseEntity<List<TempLecture>> tempGetOtherCLassList() {
        try {
            String year = "2024";
            String season = Constants.SEASONCODES[0][0]; // 1학기
            String[] otherList = Arrays.stream(Constants.SUBCODES)
                    .map(row -> row[0])
                    .toArray(String[]::new);

            List<TempLecture> classes = classUseCase.getClassList(otherList, year, season);
            return ResponseEntity.ok(classes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ArrayList<>());
        }
    }

    @GetMapping("/syllabus")
    public ResponseEntity<TempSyllabus> tempGetSyllabus() {
        try {
            String year = "2024";
            String season = Constants.SEASONCODES[0][0]; // 1학기
            String subjectCode = "CLTR0090"; // 현대사회와법
            TempSyllabus syllabus = syllabusUseCase.getSyllabus(year, season, subjectCode);
            return ResponseEntity.ok(syllabus);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new TempSyllabus());
        }
    }

    @GetMapping("/syllabus/schedule")
    public ResponseEntity<List<TempSchedule>> tempGetSyllabusSchedule() {
        try {
            String year = "2024";
            String season = Constants.SEASONCODES[0][0]; // 1학기
            String subjectCode = "CLTR0090"; // 현대사회와법
            List<TempSchedule> schedule = syllabusUseCase.getSchedule(year, season, subjectCode);
            return ResponseEntity.ok(schedule);
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
