package knu.team7.syllabus.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Evaluation {
    private final Long id;
    private final String attendance;    // 평가요소(출석)
    private final String midExam;       // 평가요소(중간시험)
    private final String finalExam;     // 평가요소(기말시험)
    private final String assignment;    // 평가요소(과제)
    private final String presentation;  // 평가요소(발표)
    private final String debate;        // 평가요소(토론)
    private final String safetyEdu;     // 평가요소(안전교육)
    private final String etc;           // 평가요소(기타)
    private final String total;         // 평가요소(총합)
    private final Course course;

    @Builder
    public Evaluation(Long id, String attendance, String midExam, String finalExam, String assignment, String presentation, String debate, String safetyEdu, String etc, String total, Course course) {
        this.id = id;
        this.attendance = attendance;
        this.midExam = midExam;
        this.finalExam = finalExam;
        this.assignment = assignment;
        this.presentation = presentation;
        this.debate = debate;
        this.safetyEdu = safetyEdu;
        this.etc = etc;
        this.total = total;
        this.course = course;
    }
}
