package knu.team7.syllabus.search.api;

import knu.team7.syllabus.search.api.dto.request.ScheduleRequest;
import knu.team7.syllabus.search.api.dto.response.ApiResponse;
import knu.team7.syllabus.search.api.dto.response.ScheduleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ScheduleController {
    @GetMapping("/schedule")
    public ResponseEntity<ApiResponse<?>> getSchedule(@ModelAttribute ScheduleRequest scheduleRequest) {
        try {
            List<ScheduleResponse> response = new ArrayList<>();
            return new ResponseEntity<>(ApiResponse.builder()
                    .success(true)
                    .data(response)
                    .build(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.builder()
                    .success(false)
                    .error(e.getMessage())
                    .data(null)
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }
}
