package knu.team7.syllabus.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evaluation")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String attendance;    // 평가요소(출석)
    private String midExam;       // 평가요소(중간시험)
    private String finalExam;     // 평가요소(기말시험)
    private String assignment;    // 평가요소(과제)
    private String presentation;  // 평가요소(발표)
    private String debate;        // 평가요소(토론)
    private String safetyEdu;     // 평가요소(안전교육)
    private String etc;           // 평가요소(기타)
    private String total;         // 평가요소(총합)

    @OneToOne
    @JoinColumn(name = "course")
    private CourseJpaEntity courseJpaEntity;    // 강좌번호

    @Builder
    public EvaluationJpaEntity(String attendance, String midExam, String finalExam, String assignment, String presentation, String debate, String safetyEdu, String etc, String total, CourseJpaEntity courseJpaEntity) {
        this.attendance = attendance;
        this.midExam = midExam;
        this.finalExam = finalExam;
        this.assignment = assignment;
        this.presentation = presentation;
        this.debate = debate;
        this.safetyEdu = safetyEdu;
        this.etc = etc;
        this.total = total;
        this.courseJpaEntity = courseJpaEntity;
    }
}
