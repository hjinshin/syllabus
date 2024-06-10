package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity(name = "FetchEvaluationJpaEntity")
@Table(name = "evaluation", uniqueConstraints = {@UniqueConstraint(columnNames = "course")})
@Getter
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

    @OneToOne
    @JoinColumn(name = "course")
    private CourseJpaEntity courseJpaEntity;    // 강좌번호

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvaluationJpaEntity that = (EvaluationJpaEntity) o;
        return Objects.equals(courseJpaEntity.getId(), that.courseJpaEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseJpaEntity);
    }

    @Builder
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
