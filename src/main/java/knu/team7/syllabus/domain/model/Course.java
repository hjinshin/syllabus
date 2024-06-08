package knu.team7.syllabus.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Course {
    private final Long id;
    private final String crseNo;      // 강좌번호
    private final String year;        // 개설연도
    private final String season;      // 개설학기

    @Builder
    public Course(Long id, String crseNo, String year, String season) {
        this.id = id;
        this.crseNo = crseNo;
        this.year = year;
        this.season = season;
    }
}
