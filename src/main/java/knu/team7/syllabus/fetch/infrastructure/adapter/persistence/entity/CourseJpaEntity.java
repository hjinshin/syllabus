package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity(name = "FetchCourseJpaEntity")
@Table(name = "course", uniqueConstraints = {@UniqueConstraint(columnNames = {"crseNo", "year", "season"})})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String crseNo;      // 과목코드
    private int year;        // 개설연도
    private String season;      // 개설학기

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_code")
    private SubjectCodeJpaEntity subjectCodeJpaEntity;  // 강좌번호

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseJpaEntity that = (CourseJpaEntity) o;
        return year == that.year && Objects.equals(crseNo, that.crseNo) && Objects.equals(season, that.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crseNo, year, season);
    }

    @Builder
    public CourseJpaEntity(Long id, String crseNo, int year, String season, SubjectCodeJpaEntity subjectCodeJpaEntity) {
        this.id = id;
        this.crseNo = crseNo;
        this.year = year;
        this.season = season;
        this.subjectCodeJpaEntity = subjectCodeJpaEntity;
    }
}