package knu.team7.syllabus.application.port.in.command;

import knu.team7.syllabus.domain.model.Course;
import lombok.Builder;

@Builder
public record EvaluationCommand(String attendance, String midExam, String finalExam,
                                String assignment, String presentation, String debate,
                                String safetyEdu, String etc, String total, Course course) {
}
