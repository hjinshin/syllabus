package knu.team7.syllabus.search.api.dto.request;

import lombok.Getter;

@Getter
public class ScheduleRequest {
    private String crseNo;      // 과목코드
    private int year;        // 개설연도
    private String season;      // 개설학기
    private String doPlan;      // 작성언어
}
