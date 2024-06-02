package knu.team7.syllabus.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TempSchedule {
    private String oriLssnsGoalLrnngCntns;      // 수업목표 및 학습내용
    private String oriLssnsMethdMediaCntns;     // 수업방법 및 매체
    private String oriTaskRsrchQstnCntns;       // 과제 및 연구문제
    private String weekSn;                      // 주차(1부터 있지는 않아서 신뢰도 낮음)
    private String oriRmrk;                     // 비고

    public TempSchedule() {
    }

    @Builder
    public TempSchedule(String oriLssnsGoalLrnngCntns, String oriLssnsMethdMediaCntns, String oriTaskRsrchQstnCntns, String weekSn, String oriRmrk) {
        this.oriLssnsGoalLrnngCntns = oriLssnsGoalLrnngCntns;
        this.oriLssnsMethdMediaCntns = oriLssnsMethdMediaCntns;
        this.oriTaskRsrchQstnCntns = oriTaskRsrchQstnCntns;
        this.weekSn = weekSn;
        this.oriRmrk = oriRmrk;
    }
}
