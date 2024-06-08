package knu.team7.syllabus.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String crseNo;      // 강좌번호
    private int year;        // 개설연도
    private String season;      // 개설학기

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_code")
    private SubjectCodeJpaEntity subjectCodeJpaEntity;  // 과목코드

    @Builder
    public CourseJpaEntity(Long id, String crseNo, int year, String season, SubjectCodeJpaEntity subjectCodeJpaEntity) {
        this.id = id;
        this.crseNo = crseNo;
        this.year = year;
        this.season = season;
        this.subjectCodeJpaEntity = subjectCodeJpaEntity;
    }
}
