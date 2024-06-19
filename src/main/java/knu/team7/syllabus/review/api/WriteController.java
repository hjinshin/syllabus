package knu.team7.syllabus.review.api;

import knu.team7.syllabus.review.api.dto.request.WriteRequest;
import knu.team7.syllabus.review.api.dto.response.ApiResponse;
import knu.team7.syllabus.review.application.port.in.WriteCommand;
import knu.team7.syllabus.review.application.usecase.WriteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class WriteController {
    private final WriteUseCase writeUseCase;
    @PostMapping("/review")
    public ResponseEntity<ApiResponse<?>> postReview(@AuthenticationPrincipal Jwt jwt,
                                                     @RequestBody WriteRequest request) {
        try {
            // jwt에서 userId 추출
            String userId = jwt.getSubject();
            WriteCommand command = WriteCommandMapper(request, userId);
            writeUseCase.writeReview(command);
            return new ResponseEntity<>(ApiResponse.builder()
                    .success(true)
                    .data(null)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.builder()
                    .success(false)
                    .error(e.getMessage())
                    .data(null)
                    .build(), HttpStatus.OK);
        }
    }

    private WriteCommand WriteCommandMapper(WriteRequest request, String userId) {
        return WriteCommand.builder()
                .userId(userId)
                .year(request.getYear())
                .season(request.getSeason())
                .crseNo(request.getCrseNo())
                .profNm(request.getProfNm())
                .rating(request.getRating())
                .contents(request.getContents())
                .build();
    }
}
