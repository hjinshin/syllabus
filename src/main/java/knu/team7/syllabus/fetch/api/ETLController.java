package knu.team7.syllabus.fetch.api;

import knu.team7.syllabus.fetch.api.dto.response.FetchResponse;
import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.fetch.application.usecase.DataFetchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ETLController {
    private final DataFetchUseCase dataFetchUseCase;

    @GetMapping("/fetch")
    public ResponseEntity<FetchResponse> getFetch(@RequestParam(value = "year") int year,
                                                  @RequestParam(value = "season") String season) {
        try {
            Optional<String> seasonCode = Optional.ofNullable(Constants.SEASONCODES.get(season));
            if (seasonCode.isEmpty()) {
                return ResponseEntity.ok(FetchResponse.builder()
                        .success(false)
                        .note("season error")
                        .build());
            }

//            dataFetchUseCase.fetchData(year, seasonCode.get());
            dataFetchUseCase.fetchGEData(year, seasonCode.get());
            dataFetchUseCase.fetchOtherData(year, seasonCode.get());


            return ResponseEntity.ok(FetchResponse.builder()
                    .success(true)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(FetchResponse.builder()
                    .success(false)
                    .note(e.getMessage())
                    .build());
        }
    }

}
