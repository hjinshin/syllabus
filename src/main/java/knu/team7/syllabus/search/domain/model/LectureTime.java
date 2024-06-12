package knu.team7.syllabus.search.domain.model;

import knu.team7.syllabus.core.type.DayType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LectureTime {
    private final DayType day;        // 요일 ex) MON("월"),TUE("화"),WED("수"),THU("목"),FRI("금"), SAT("토"),SUN("일");
    private final String timeCode;

    @Builder
    public LectureTime(DayType day, String timeCode) {
        this.day = day;
        this.timeCode = timeCode;
    }
}
