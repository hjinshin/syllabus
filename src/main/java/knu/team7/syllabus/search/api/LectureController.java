package knu.team7.syllabus.search.api;

import knu.team7.syllabus.search.api.dto.request.LectureRequest;
import knu.team7.syllabus.search.api.dto.response.ApiResponse;
import knu.team7.syllabus.search.api.dto.response.LectureResponse;
import knu.team7.syllabus.search.application.port.in.command.LectureCommand;
import knu.team7.syllabus.search.application.usecase.LoadLectureUseCase;
import knu.team7.syllabus.search.domain.model.Lecture;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class LectureController {
    private final LoadLectureUseCase lectureUseCase;
    @PostMapping("/lecture")
    public ResponseEntity<ApiResponse<?>> getLectures(@RequestBody LectureRequest request) {
        try {
            LectureCommand command = lectureCommandMapper(request);

            List<Lecture> lectureList = lectureUseCase.loadLectures(command);

            List<LectureResponse> response = lectureList.stream()
                    .map(this::lectureResponseMapper).toList();

            return new ResponseEntity<>(ApiResponse.builder()
                    .success(true)
                    .data(response)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.builder()
                    .success(false)
                    .error(e.getMessage())
                    .data(null)
                    .build(), HttpStatus.OK);
        }
    }

    @GetMapping("/lecture/select")
    public ResponseEntity<ApiResponse<?>> getLectureByCrseNos(@RequestParam("year") int year,
                                                         @RequestParam("season") String season,
                                                         @RequestParam("crseno") List<String> crseNoList) {
        try {
            List<Lecture> lectureList = lectureUseCase.loadLecturesByCrseNos(year, season, crseNoList);
            List<LectureResponse> response = lectureList.stream()
                    .map(this::lectureResponseMapper).toList();
            return new ResponseEntity<>(ApiResponse.builder()
                    .success(true)
                    .data(response)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.builder()
                    .success(false)
                    .error(e.getMessage())
                    .data(null)
                    .build(), HttpStatus.OK);
        }
    }

    private LectureCommand lectureCommandMapper(LectureRequest request) {
        return LectureCommand.builder()
                .year(request.getYear()).season(request.getSeason()).crseNo(request.getCrseNo())
                .sbjctNm(request.getSbjctNm()).subjctCd(request.getSubjctCd()).professor(request.getProfessor())
                .college(request.getCollege()).depart(request.getDepart()).lang(request.getLang())
                .sbjctSection(request.getSbjctSection()).lSect(request.getLSect()).mSect(request.getMSect())
                .sSect(request.getSSect()).days(request.getDays()).evaluation(request.getEvaluation())
                .isRemote(request.isRemote()).isHumanities(request.isHumanities()).isSdg(request.isSdg())
                .isFlipped(request.isFlipped()).isNU(request.isNU()).isDgKp(request.isDgKp()).isSu(request.isSu())
                .build();
    }
    private LectureResponse lectureResponseMapper(Lecture lecture) {
        return LectureResponse.builder()
                .year(lecture.getYear()).season(lecture.getSeason()).sbjSection(lecture.getSbjSection())
                .lSct(lecture.getLSct()).mSct(lecture.getMSct()).sSct(lecture.getSSct())
                .college(lecture.getCollege()).depart(lecture.getDepart()).grade(lecture.getGrade())
                .crseNo(lecture.getCrseNo()).sbjctNm(lecture.getSbjctNm()).subjctCd(lecture.getSubjctCd())
                .realLecTime(lecture.getRealLecTime()).lecTimes(lecture.getLecTimes()).credit(lecture.getCredit())
                .lecCr(lecture.getLecCr()).pracCr(lecture.getPracCr()).professor(lecture.getProfessor())
                .building(lecture.getBuilding()).room(lecture.getRoom()).capacity(lecture.getCapacity())
                .lang(lecture.getLang()).isRemote(lecture.isRemote()).preSbjet(lecture.getPreSbjet())
                .postSbjet(lecture.getPostSbjet()).evaluation(lecture.getEvaluation())
                .isHumanities(lecture.isHumanities()).isSdg(lecture.isSdg()).isFlipped(lecture.isFlipped())
                .isNU(lecture.isNU()).isDgKp(lecture.isDgKp()).isSu(lecture.isSu()).note(lecture.getNote())
                .build();
    }
}
