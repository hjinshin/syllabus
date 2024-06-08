package knu.team7.syllabus.application.port.in.command;

import knu.team7.syllabus.domain.model.Course;
import lombok.Builder;

@Builder
public record SyllabusCommand(String crseGoal, String eduGoal, String summary, String textbook, String evalMethd, String intviTimeLoc, String refer, Course course) {
}
