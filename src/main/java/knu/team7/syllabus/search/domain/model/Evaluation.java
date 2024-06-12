package knu.team7.syllabus.search.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Evaluation {
    private final float attendance;    // 평가요소(출석)
    private final float midExam;       // 평가요소(중간시험)
    private final float finalExam;     // 평가요소(기말시험)
    private final float assignment;    // 평가요소(과제)
    private final float presentation;  // 평가요소(발표)
    private final float debate;        // 평가요소(토론)
    private final float safetyEdu;     // 평가요소(안전교육)
    private final float etc;           // 평가요소(기타)
    private final float total;         // 평가요소(총합)

    @Builder
    public Evaluation(float attendance, float midExam, float finalExam, float assignment, float presentation, float debate, float safetyEdu, float etc, float total) {
        this.attendance = attendance;
        this.midExam = midExam;
        this.finalExam = finalExam;
        this.assignment = assignment;
        this.presentation = presentation;
        this.debate = debate;
        this.safetyEdu = safetyEdu;
        this.etc = etc;
        this.total = total;
    }
}