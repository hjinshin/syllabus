package knu.team7.syllabus.search.api.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ScheduleRequest {
    private final String crseNo;      // 과목코드
    private final int year;        // 개설연도
    private final String season;      // 개설학기
    private final String doPlan;      // 작성언어

    @Builder
    public ScheduleRequest(String crseNo, int year, String season, String doPlan) {
        this.crseNo = crseNo;
        this.year = year;
        this.season = season;
        this.doPlan = doPlan;
    }
}
