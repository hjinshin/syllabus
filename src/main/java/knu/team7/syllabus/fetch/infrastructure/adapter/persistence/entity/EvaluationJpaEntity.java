package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity(name = "FetchEvaluationJpaEntity")
@Table(name = "evaluation", uniqueConstraints = {@UniqueConstraint(columnNames = "course_id")})
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EvaluationJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float attendance;    // 평가요소(출석)
    private float midExam;       // 평가요소(중간시험)
    private float finalExam;     // 평가요소(기말시험)
    private float assignment;    // 평가요소(과제)
    private float presentation;  // 평가요소(발표)
    private float debate;        // 평가요소(토론)
    private float safetyEdu;     // 평가요소(안전교육)
    private float etc;           // 평가요소(기타)
    private float total;         // 평가요소(총합)

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private CourseJpaEntity courseJpaEntity;    // 과목코드

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvaluationJpaEntity that = (EvaluationJpaEntity) o;
        return Float.compare(that.attendance, attendance) == 0 && Float.compare(that.midExam, midExam) == 0 && Float.compare(that.finalExam, finalExam) == 0 && Float.compare(that.assignment, assignment) == 0 && Float.compare(that.presentation, presentation) == 0 && Float.compare(that.debate, debate) == 0 && Float.compare(that.safetyEdu, safetyEdu) == 0 && Float.compare(that.etc, etc) == 0 && Float.compare(that.total, total) == 0 && Objects.equals(courseJpaEntity, that.courseJpaEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attendance, midExam, finalExam, assignment, presentation, debate, safetyEdu, etc, total, courseJpaEntity);
    }

    @Builder(toBuilder = true)
    public EvaluationJpaEntity(Long id, float attendance, float midExam, float finalExam, float assignment, float presentation, float debate, float safetyEdu, float etc, float total, CourseJpaEntity courseJpaEntity) {
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
        this.courseJpaEntity = courseJpaEntity;
    }
}
