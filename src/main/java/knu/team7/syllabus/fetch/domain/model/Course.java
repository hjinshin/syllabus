package knu.team7.syllabus.fetch.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Course {
    private final Long id;
    private final String crseNo;      // 과목코드
    private final int year;        // 개설연도
    private final String season;      // 개설학기

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return year == course.year && Objects.equals(crseNo, course.crseNo) && Objects.equals(season, course.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crseNo, year, season);
    }

    @Builder
    public Course(Long id, String crseNo, int year, String season) {
        this.id = id;
        this.crseNo = crseNo;
        this.year = year;
        this.season = season;
    }
}
