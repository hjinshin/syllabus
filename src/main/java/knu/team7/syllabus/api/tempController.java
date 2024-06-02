package knu.team7.syllabus.api;

import com.google.gson.JsonObject;
import knu.team7.core.Constants;
import knu.team7.syllabus.application.port.in.command.CodeCommand;
import knu.team7.syllabus.application.usecase.ClassUseCase;
import knu.team7.syllabus.application.usecase.ListUseCase;
import knu.team7.syllabus.application.usecase.SyllabusUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class tempController {

    private final ListUseCase listUseCase;
    private final ClassUseCase classUseCase;
    private final SyllabusUseCase syllabusUseCase;
    @GetMapping("/ge/list")
    public ResponseEntity<String> tempGetGEList() {
        try {
            List<CodeCommand> geList = listUseCase.getGEList();
            return ResponseEntity.ok(geList.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("error");
        }
    }

    @GetMapping("/ge")
    public ResponseEntity<String> tempGetGEClassList() {
        try {
            String year = "2024";
            String season = Constants.SEASONCODES[0]; // 1학기
            List<CodeCommand> geList = listUseCase.getGEList();
            List<JsonObject> classes = classUseCase.getClassList(geList, year, season);
            return ResponseEntity.ok(classes.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("error");
        }
    }
    @GetMapping("/other")
    public ResponseEntity<String> tempGetOtherCLassList() {
        try {
            String year = "2024";
            String season = Constants.SEASONCODES[0]; // 1학기
            String[] otherList = Constants.SUBCODES;
            List<JsonObject> classes = classUseCase.getClassList(otherList, year, season);
            return ResponseEntity.ok(classes.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("error");
        }
    }

    @GetMapping("/syllabus")
    public ResponseEntity<String> tempGetSyllabus() {
        try {
            String year = "2024";
            String season = Constants.SEASONCODES[0]; // 1학기
            String subjectCode = "CLTR0090"; // 현대사회와법
            String syllabus = syllabusUseCase.getSyllabus(year, season, subjectCode);
            return ResponseEntity.ok(syllabus);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("error");
        }
    }

    @GetMapping("/syllabus/schedule")
    public ResponseEntity<String> tempGetSyllabusSchedule() {
        try {
            String year = "2024";
            String season = Constants.SEASONCODES[0]; // 1학기
            String subjectCode = "CLTR0090"; // 현대사회와법
            List<JsonObject> schedule = syllabusUseCase.getSchedule(year, season, subjectCode);
            return ResponseEntity.ok(schedule.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("error");
        }
    }
}
