package knu.team7.syllabus.api;

import com.google.gson.JsonObject;
import knu.team7.core.Constants;
import knu.team7.syllabus.application.port.in.command.CodeCommand;
import knu.team7.syllabus.application.usecase.ClassUseCase;
import knu.team7.syllabus.application.usecase.ListUseCase;
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
    @GetMapping("/ge")
    public ResponseEntity<String> tempGetGEList() {
        try {
            String year = "2024";
            String season = "CMBS001400001"; // 1학기
            List<CodeCommand> geList = listUseCase.getGEList();
            List<JsonObject> classes = classUseCase.getClassList(geList, year, season);
            return ResponseEntity.ok(classes.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("error");
        }
    }
    @GetMapping("/other")
    public ResponseEntity<String> tempGetOtherList() {
        try {
            String year = "2024";
            String season = "CMBS001400001"; // 1학기
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

            return ResponseEntity.ok("success");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("error");
        }
    }
}
