package knu.team7.syllabus.search.api.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ScheduleResponse {
    private final String lssnsGoalCntns;           // 수업목표 및 학습내용
    private final String lssnsMethd;               // 수업방법 및 매체
    private final String rsrchCntns;               // 과제 및 연구문제
    private final String weekSn;                   // 주차(1부터 있지는 않아서 신뢰도 낮음)
    private final String weekNote;                 // 비고
    private final String doPlan;                   // 작성언어


    @Builder
    public ScheduleResponse(String lssnsGoalCntns, String lssnsMethd, String rsrchCntns, String weekSn, String weekNote, String doPlan) {
        this.lssnsGoalCntns = lssnsGoalCntns;
        this.lssnsMethd = lssnsMethd;
        this.rsrchCntns = rsrchCntns;
        this.weekSn = weekSn;
        this.weekNote = weekNote;
        this.doPlan = doPlan;
    }
}
