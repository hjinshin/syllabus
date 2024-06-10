package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity(name = "FetchScheduleJpaEntity")
@Table(name = "schedule", uniqueConstraints = {@UniqueConstraint(columnNames = {"doPlan", "course"})})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String doPlan;              // 작성언어
    @Column(columnDefinition = "TEXT")
    private String lssnsGoalCntns;           // 수업목표 및 학습내용
    @Column(columnDefinition = "TEXT")
    private String lssnsMethd;               // 수업방법 및 매체
    @Column(columnDefinition = "TEXT")
    private String rsrchCntns;               // 과제 및 연구문제
    private String weekSn;                   // 주차(1부터 있지는 않아서 신뢰도 낮음)
    @Column(columnDefinition = "TEXT")
    private String weekNote;                 // 비고

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course")
    private CourseJpaEntity courseJpaEntity;    // 강좌번호

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScheduleJpaEntity that = (ScheduleJpaEntity) o;
        return Objects.equals(doPlan, that.doPlan) &&
                courseJpaEntity.getYear() == that.courseJpaEntity.getYear() &&
                Objects.equals(courseJpaEntity.getCrseNo(), that.courseJpaEntity.getCrseNo()) &&
                Objects.equals(courseJpaEntity.getSeason(), that.courseJpaEntity.getSeason());
    }

    @Override
    public int hashCode() {
        return Objects.hash(doPlan, courseJpaEntity);
    }

    @Builder
    public ScheduleJpaEntity(Long id, String doPlan, String lssnsGoalCntns, String lssnsMethd, String rsrchCntns, String weekSn, String weekNote, CourseJpaEntity courseJpaEntity) {
        this.id = id;
        this.doPlan = doPlan;
        this.lssnsGoalCntns = lssnsGoalCntns;
        this.lssnsMethd = lssnsMethd;
        this.rsrchCntns = rsrchCntns;
        this.weekSn = weekSn;
        this.weekNote = weekNote;
        this.courseJpaEntity = courseJpaEntity;
    }
}
