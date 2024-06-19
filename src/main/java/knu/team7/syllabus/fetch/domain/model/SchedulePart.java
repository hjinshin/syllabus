package knu.team7.syllabus.fetch.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class SchedulePart {
    private final Long course_id;
    private final String doPlan;
    private final int no;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulePart that = (SchedulePart) o;
        return no == that.no && Objects.equals(course_id, that.course_id) && Objects.equals(doPlan, that.doPlan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course_id, doPlan, no);
    }

    @Builder
    public SchedulePart(Long course_id, String doPlan, int no) {
        this.course_id = course_id;
        this.doPlan = doPlan;
        this.no = no;
    }
}
