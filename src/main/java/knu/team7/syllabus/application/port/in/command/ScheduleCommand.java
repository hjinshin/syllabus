package knu.team7.syllabus.application.port.in.command;

import knu.team7.syllabus.domain.model.Course;
import lombok.Builder;

@Builder
public record ScheduleCommand(String lssnsGoalCntns, String lssnsMethd, String rsrchCntns, String weekSn, String weekNote, Course course) {
}
