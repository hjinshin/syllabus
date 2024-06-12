package knu.team7.syllabus.fetch.application.port.in.command;

import knu.team7.syllabus.fetch.domain.model.Course;
import lombok.Builder;

@Builder
public record ScheduleCommand(int no, String lssnsGoalCntns, String lssnsMethd, String rsrchCntns, String weekSn, String weekNote, String doPlan, Course course) {
}
