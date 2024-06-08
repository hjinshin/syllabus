package knu.team7.syllabus.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import knu.team7.syllabus.core.type.DayType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Set;

@Entity
@Table(name = "lecture_time")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureTimeJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DayType day;    // 요일
    private LocalTime startTime;  // 시작 시간
    private LocalTime endTime;    // 종료 시간

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private LectureJpaEntity lecture;  // 강의 시간이 속한 교과목

    @ElementCollection
    @CollectionTable(name = "time_code", joinColumns = @JoinColumn(name = "lecture_time_id"))
    @Column(name = "code")
    private Set<String> timeCodes; // 시간 코드

    @Builder
    public LectureTimeJpaEntity(DayType day, LocalTime startTime, LocalTime endTime, LectureJpaEntity lecture, Set<String> timeCodes) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lecture = lecture;
        this.timeCodes = timeCodes;
    }
}
