package knu.team7.syllabus.fetch.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Syllabus {
    private final String crseNo;
    private final String crseGoal;       // 강의목표
    private final String eduGoal;        // 교육목표
    private final String summary;        // 강의개요
    private final String textbook;       // 교재 및 참고문헌 직접입력
    private final String evalMethd;      // 평가방법
    private final String intviTimeLoc;   // 상담장소/시간
    private final String refer;           // 수강 참고사항
    private final String lang;          // 강의언어

    private final String profNm;          // 담당교수
    private final String profTel;         // 교수 연락처
    private final String profEmail;       // 교수 이메일

    private final String preSbjet;    // 권장선수과목
    private final String postSbjet;   // 권장선수과목

    private final String attendance;    // 평가요소(출석)
    private final String midExam;       // 평가요소(중간시험)
    private final String finalExam;     // 평가요소(기말시험)
    private final String assignment;    // 평가요소(과제)
    private final String presentation;  // 평가요소(발표)
    private final String debate;        // 평가요소(토론)
    private final String safetyEdu;     // 평가요소(안전교육)
    private final String etc;           // 평가요소(기타)
    private final String total;         // 평가요소(총합)
    private final String doPlan;
    private final Course course;

    @Builder
    public Syllabus(String crseNo, String crseGoal, String eduGoal, String summary, String textbook, String evalMethd, String intviTimeLoc, String refer, String lang, String profNm, String profTel, String profEmail, String preSbjet, String postSbjet, String attendance, String midExam, String finalExam, String assignment, String presentation, String debate, String safetyEdu, String etc, String total, String doPlan, Course course) {
        this.crseNo = crseNo;
        this.crseGoal = crseGoal;
        this.eduGoal = eduGoal;
        this.summary = summary;
        this.textbook = textbook;
        this.evalMethd = evalMethd;
        this.intviTimeLoc = intviTimeLoc;
        this.refer = refer;
        this.lang = lang;
        this.profNm = profNm;
        this.profTel = profTel;
        this.profEmail = profEmail;
        this.preSbjet = preSbjet;
        this.postSbjet = postSbjet;
        this.attendance = attendance;
        this.midExam = midExam;
        this.finalExam = finalExam;
        this.assignment = assignment;
        this.presentation = presentation;
        this.debate = debate;
        this.safetyEdu = safetyEdu;
        this.etc = etc;
        this.total = total;
        this.doPlan = doPlan;
        this.course = course;
    }

}
