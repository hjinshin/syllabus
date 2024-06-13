package knu.team7.syllabus.search.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity(name = "SearchCourseJpaEntity")
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
    @JoinColumn(name = "subject_id")
    private SubjectJpaEntity subjectJpaEntity;  // 강좌번호

    @OneToOne(mappedBy = "courseJpaEntity", cascade = CascadeType.ALL)
    private EvaluationJpaEntity evaluationJpaEntity;

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
    public CourseJpaEntity(Long id, String crseNo, int year, String season, SubjectJpaEntity subjectJpaEntity, EvaluationJpaEntity evaluationJpaEntity) {
        this.id = id;
        this.crseNo = crseNo;
        this.year = year;
        this.season = season;
        this.subjectJpaEntity = subjectJpaEntity;
        this.evaluationJpaEntity = evaluationJpaEntity;
    }

    @Override
    public String toString() {
        return "CourseJpaEntity{" +
                "id=" + id +
                ", crseNo='" + crseNo + '\'' +
                ", year=" + year +
                ", season='" + season + '\'' +
                ", subjectJpaEntity=" + subjectJpaEntity +
                ", evaluationJpaEntity=" + evaluationJpaEntity +
                '}';
    }
}
