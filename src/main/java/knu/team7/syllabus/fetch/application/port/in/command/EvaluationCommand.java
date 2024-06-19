package knu.team7.syllabus.fetch.application.port.in.command;

import lombok.Builder;

import java.util.Objects;

@Builder
public record EvaluationCommand(String crseNo, int year, String season, float attendance, float midExam,
                                float finalExam, float assignment, float presentation, float debate,
                                float safetyEdu, float etc, float total) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EvaluationCommand that = (EvaluationCommand) o;
        return Objects.equals(crseNo, that.crseNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crseNo);
    }
}
