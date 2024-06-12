package knu.team7.syllabus.search.api;

import knu.team7.syllabus.search.api.dto.request.SyllabusRequest;
import knu.team7.syllabus.search.api.dto.response.ApiResponse;
import knu.team7.syllabus.search.api.dto.response.SyllabusResponse;
import knu.team7.syllabus.search.application.port.in.command.SyllabusCommand;
import knu.team7.syllabus.search.application.usecase.LoadSyllabusUseCase;
import knu.team7.syllabus.search.domain.model.Syllabus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SyllabusController {
    private final LoadSyllabusUseCase loadSyllabusUseCase;
    @GetMapping("/syllabus")
    public ResponseEntity<ApiResponse<?>> getSyllabus(@ModelAttribute SyllabusRequest syllabusRequest) {
        try {
            SyllabusCommand command = SyllabusCommand.builder()
                    .doPlan(syllabusRequest.getDoPlan())
                    .year(syllabusRequest.getYear())
                    .season(syllabusRequest.getSeason())
                    .crseNo(syllabusRequest.getCrseNo())
                    .build();

            Syllabus syllabus = loadSyllabusUseCase.loadSyllabus(command);
            return new ResponseEntity<>(ApiResponse.builder()
                    .success(true)
                    .data(SyllabusResponse.builder()
                            .crseNo(syllabus.getCrseNo())
                            .crseGoal(syllabus.getCrseGoal())
                            .eduGoal(syllabus.getEduGoal())
                            .summary(syllabus.getSummary())
                            .textbook(syllabus.getTextbook())
                            .evalMethd(syllabus.getEvalMethd())
                            .intviTimeLoc(syllabus.getIntviTimeLoc())
                            .refer(syllabus.getRefer())
                            .build())
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
