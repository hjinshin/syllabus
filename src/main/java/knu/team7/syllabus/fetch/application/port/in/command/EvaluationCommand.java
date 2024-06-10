package knu.team7.syllabus.fetch.application.port.in.command;

import knu.team7.syllabus.fetch.domain.model.Course;
import lombok.Builder;

@Builder
public record EvaluationCommand(float attendance, float midExam, float finalExam,
                                float assignment, float presentation, float debate,
                                float safetyEdu, float etc, float total, Course course) {
}
