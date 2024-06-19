package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import knu.team7.syllabus.core.type.DayType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "FetchLectureTimeJpaEntity")
@Table(name = "lecture_time")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureTimeJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DayType day;    // 요일
    private String timeCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private LectureJpaEntity lecture;  // 강의 시간이 속한 교과목

    public void setLectureJpaEntity(LectureJpaEntity entity) {
        this.lecture = entity;
    }

    @Builder
    public LectureTimeJpaEntity(Long id, DayType day, String timeCode, LectureJpaEntity lecture) {
        this.id = id;
        this.day = day;
        this.timeCode = timeCode;
        this.lecture = lecture;
    }
}
