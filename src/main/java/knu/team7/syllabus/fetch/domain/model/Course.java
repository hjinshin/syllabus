package knu.team7.syllabus.fetch.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Course {
    private final Long id;
    private final String crseNo;      // 강좌번호
    private final String year;        // 개설연도
    private final String season;      // 개설학기

    private final SubjectCode subjectCode;


    @Builder
    public Course(Long id, String crseNo, String year, String season, SubjectCode subjectCode) {
        this.id = id;
        this.crseNo = crseNo;
        this.year = year;
        this.season = season;
        this.subjectCode = subjectCode;
    }
}
