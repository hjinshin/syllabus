package knu.team7.syllabus.search.api;

import knu.team7.syllabus.search.api.dto.request.ScheduleRequest;
import knu.team7.syllabus.search.api.dto.response.ApiResponse;
import knu.team7.syllabus.search.api.dto.response.ScheduleResponse;
import knu.team7.syllabus.search.application.port.in.command.ScheduleCommand;
import knu.team7.syllabus.search.application.usecase.LoadScheduleUseCase;
import knu.team7.syllabus.search.domain.model.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ScheduleController {
    private final LoadScheduleUseCase loadScheduleUseCase;

    @GetMapping("/schedule")
    public ResponseEntity<ApiResponse<?>> getSchedule(@ModelAttribute ScheduleRequest request) {
        try {
            ScheduleCommand command = scehduleCommandMapper(request);

            List<Schedule> scheduleList = loadScheduleUseCase.loadSchedules(command);
            List<ScheduleResponse> scheduleResponses = scheduleList.stream()
                    .map(this::scheduleResponseMapper).toList();

            return new ResponseEntity<>(ApiResponse.builder()
                    .success(true)
                    .data(scheduleResponses)
                    .build(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ApiResponse.builder()
                    .success(false)
                    .error(e.getMessage())
                    .data(null)
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }

    private ScheduleCommand scehduleCommandMapper(ScheduleRequest request) {
        return ScheduleCommand.builder()
                .crseNo(request.getCrseNo())
                .year(request.getYear())
                .season(request.getSeason())
                .doPlan(request.getDoPlan())
                .build();
    }

    private ScheduleResponse scheduleResponseMapper(Schedule schedule) {
        return ScheduleResponse.builder()
                .lssnsGoalCntns(schedule.getLssnsGoalCntns())
                .lssnsMethd(schedule.getLssnsMethd())
                .rsrchCntns(schedule.getRsrchCntns())
                .weekSn(schedule.getWeekSn())
                .weekNote(schedule.getWeekNote())
                .build();
    }

}
