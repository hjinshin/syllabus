package knu.team7.syllabus.fetch.api;

import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.fetch.api.dto.response.FetchResponse;
import knu.team7.syllabus.fetch.application.port.in.command.YearAndSeasonCommand;
import knu.team7.syllabus.fetch.application.usecase.FetchUseCase;
import knu.team7.syllabus.fetch.application.usecase.FetchYearAndSeasonUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/public/api/v1")
public class FetchController {
    private final FetchUseCase fetchUseCase;
    private final FetchYearAndSeasonUseCase fetchYearAndSeasonUseCase;

    @GetMapping("/fetch")
    public ResponseEntity<FetchResponse> getFetch() {
        try {
            YearAndSeasonCommand command = fetchYearAndSeasonUseCase.fetchYearAndSeason();

            getFetchByYearAndSeason(command.year(), command.season());

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


    @GetMapping("/fetch/select")
    public ResponseEntity<FetchResponse> getFetchByYearAndSeason(@RequestParam(value = "year") int year,
                                                  @RequestParam(value = "season") String season) {
        try {
            Optional<String> seasonCode = Optional.ofNullable(Constants.SEASONCODES.get(season));
            if (seasonCode.isEmpty()) {
                return ResponseEntity.ok(FetchResponse.builder()
                        .success(false)
                        .note("season error")
                        .build());
            }

            fetchUseCase.fetchGE(year, season);
            fetchUseCase.fetchOther(year, season);

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
