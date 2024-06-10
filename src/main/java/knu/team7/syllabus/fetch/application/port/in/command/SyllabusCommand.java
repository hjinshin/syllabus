package knu.team7.syllabus.fetch.application.port.in.command;

import knu.team7.syllabus.fetch.domain.model.Course;
import lombok.Builder;

@Builder
public record SyllabusCommand(String crseGoal, String eduGoal, String summary, String textbook, String evalMethd, String intviTimeLoc, String refer, String doPlan, Course course) {
}
