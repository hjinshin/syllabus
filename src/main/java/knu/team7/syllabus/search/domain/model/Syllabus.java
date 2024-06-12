package knu.team7.syllabus.search.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Syllabus {
    private final String crseNo;    // 강좌번호
    private final String crseGoal;       // 강의목표
    private final String eduGoal;        // 교육목표
    private final String summary;        // 강의개요
    private final String textbook;       // 교재 및 참고문헌 직접입력
    private final String evalMethd;      // 평가방법
    private final String intviTimeLoc;   // 상담장소/시간
    private final String refer;           // 수강 참고사항
    private final String doPlan;      // 작성언어

    @Builder
    public Syllabus(String crseNo, String crseGoal, String eduGoal, String summary, String textbook, String evalMethd, String intviTimeLoc, String refer, String doPlan) {
        this.crseNo = crseNo;
        this.crseGoal = crseGoal;
        this.eduGoal = eduGoal;
        this.summary = summary;
        this.textbook = textbook;
        this.evalMethd = evalMethd;
        this.intviTimeLoc = intviTimeLoc;
        this.refer = refer;
        this.doPlan = doPlan;
    }
}
