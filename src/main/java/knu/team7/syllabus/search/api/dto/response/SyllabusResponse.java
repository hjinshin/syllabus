package knu.team7.syllabus.search.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class SyllabusResponse{
    private final String crseNo;    // 강좌번호
    private final String crseGoal;       // 강의목표
    private final String eduGoal;        // 교육목표
    private final String summary;        // 강의개요
    private final String textbook;       // 교재 및 참고문헌 직접입력
    private final String evalMethd;      // 평가방법
    private final String intviTimeLoc;   // 상담장소/시간
    private final String refer;           // 수강 참고사항
    private final String doPlan;      // 작성언어
    private final List<ScheduleDTO> schedules;    // 주별강의


    @Builder
    public SyllabusResponse(String crseNo, String crseGoal, String eduGoal, String summary, String textbook, String evalMethd, String intviTimeLoc, String refer, String doPlan, List<ScheduleDTO> schedules) {
        this.crseNo = crseNo;
        this.crseGoal = crseGoal;
        this.eduGoal = eduGoal;
        this.summary = summary;
        this.textbook = textbook;
        this.evalMethd = evalMethd;
        this.intviTimeLoc = intviTimeLoc;
        this.refer = refer;
        this.doPlan = doPlan;
        this.schedules = schedules;
    }
}

@Getter
class ScheduleDTO {
    private final String lssnsGoalCntns;           // 수업목표 및 학습내용
    private final String lssnsMethd;               // 수업방법 및 매체
    private final String rsrchCntns;               // 과제 및 연구문제
    private final String weekSn;                   // 주차(1부터 있지는 않아서 신뢰도 낮음)
    private final String weekNote;                 // 비고


    @Builder
    public ScheduleDTO(String lssnsGoalCntns, String lssnsMethd, String rsrchCntns, String weekSn, String weekNote) {
        this.lssnsGoalCntns = lssnsGoalCntns;
        this.lssnsMethd = lssnsMethd;
        this.rsrchCntns = rsrchCntns;
        this.weekSn = weekSn;
        this.weekNote = weekNote;
    }
}
