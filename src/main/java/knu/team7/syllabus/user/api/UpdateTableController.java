package knu.team7.syllabus.user.api;

import knu.team7.syllabus.user.api.dto.ApiResponse;
import knu.team7.syllabus.user.api.dto.TableRequest;
import knu.team7.syllabus.user.application.usecase.UpdateTableUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UpdateTableController {
    private final UpdateTableUseCase updateTableUseCase;

    @PutMapping("/table")
    public ResponseEntity<ApiResponse<?>> updateTimeTable(@AuthenticationPrincipal Jwt jwt,
                                                          @RequestBody TableRequest request) {
        try {
            // jwt에서 userId 추출
            String userId = jwt.getSubject();
            updateTableUseCase.updateUserTable(request.getCrseNos(), request.getYear(), request.getSeason(), userId);
            return new ResponseEntity<>(ApiResponse.builder()
                    .success(true)
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
