package knu.team7.syllabus.search.api;

import knu.team7.syllabus.search.api.dto.request.LectureRequest;
import knu.team7.syllabus.search.api.dto.response.ApiResponse;
import knu.team7.syllabus.search.api.dto.response.LectureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LectureController {
    @PostMapping("/lecture")
    public ResponseEntity<ApiResponse<?>> getLectures(@ModelAttribute LectureRequest lectureRequest) {
        try {
            List<LectureResponse> response = new ArrayList<>();
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
