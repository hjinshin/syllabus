package knu.team7.syllabus.user.api;

import knu.team7.syllabus.user.api.dto.ApiResponse;
import knu.team7.syllabus.user.application.usecase.LoadTableUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class LoadTableController {
    private final LoadTableUseCase loadTableUseCase;


    @GetMapping("/table")
    public ResponseEntity<ApiResponse<?>> getUserTimeTable(@AuthenticationPrincipal Jwt jwt,
                                                           @RequestParam("year")int year,
                                                           @RequestParam("season")String season) {
        try {
            // jwt에서 userId 추출
            String userId = jwt.getSubject();
            List<String> crseNos = loadTableUseCase.loadTables(year, season, userId);
            return new ResponseEntity<>(ApiResponse.builder()
                    .success(true)
                    .data(crseNos)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.builder()
                    .success(false)
                    .error(e.getMessage())
                    .data(null)
                    .build(), HttpStatus.OK);
        }
    }
}
