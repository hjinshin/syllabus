package knu.team7.syllabus.fetch.application.port.in.command;

import lombok.Builder;

import java.util.Objects;

@Builder
public record CourseCommand(String crseNo, int year, String season, String subjectCd) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseCommand that = (CourseCommand) o;
        return year == that.year && Objects.equals(crseNo, that.crseNo) && Objects.equals(season, that.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crseNo, year, season);
    }
}
