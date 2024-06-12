package knu.team7.syllabus.fetch.application.port.in.command;

import lombok.Builder;

@Builder
public record EvaluationCommand(String crseNo, int year, String season, float attendance, float midExam,
                                float finalExam, float assignment, float presentation, float debate,
                                float safetyEdu, float etc, float total) {
}
