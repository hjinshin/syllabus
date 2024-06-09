package knu.team7.syllabus.domain.model;

import knu.team7.syllabus.core.type.DayType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class LectureTime {
    private final Long id;
    private final DayType day;
    private final String timeCode;

    @Builder
    public LectureTime(Long id, DayType day, String timeCode) {
        this.id = id;
        this.day = day;
        this.timeCode = timeCode;
    }

    @Override
    public String toString() {
        return "LectureTime{" +
                "id=" + id +
                ", day=" + day +
                ", timeCode='" + timeCode + '\'' +
                '}';
    }
}
