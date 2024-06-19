package knu.team7.syllabus.search.domain.model;

import knu.team7.syllabus.core.type.DayType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Day {
    private final DayType day; // 요일
    private final String timeCode; // 시간코드

    @Builder
    public Day(DayType day, String timeCode) {
        this.day = day;
        this.timeCode = timeCode;
    }
}
