package knu.team7.syllabus.api;

import com.google.gson.JsonObject;
import knu.team7.syllabus.application.port.in.command.GECodeCommand;
import knu.team7.syllabus.application.usecase.GEClassUseCase;
import knu.team7.syllabus.application.usecase.GEListUseCase;
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

    private final GEListUseCase geListUseCase;
    private final GEClassUseCase geClassUseCase;
    @GetMapping
    public ResponseEntity<String> tempGetGEList() {
        try {
            String year = "2024";
            String season = "CMBS001400001"; // 1학기
            List<GECodeCommand> list = geListUseCase.getGEList(year);
            List<JsonObject> classes = geClassUseCase.getGEClass(list, year, season);
            return ResponseEntity.ok(classes.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("error");
        }
    }
}
