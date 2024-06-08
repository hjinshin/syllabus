package knu.team7.syllabus.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "schedule")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @Builder
    public ScheduleJpaEntity(String lssnsGoalCntns, String lssnsMethd, String rsrchCntns, String weekSn, String weekNote, CourseJpaEntity courseJpaEntity) {
        this.lssnsGoalCntns = lssnsGoalCntns;
        this.lssnsMethd = lssnsMethd;
        this.rsrchCntns = rsrchCntns;
        this.weekSn = weekSn;
        this.weekNote = weekNote;
        this.courseJpaEntity = courseJpaEntity;
    }
}
