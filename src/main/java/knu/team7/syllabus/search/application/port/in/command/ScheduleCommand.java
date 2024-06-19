package knu.team7.syllabus.search.application.port.in.command;

import lombok.Builder;

@Builder
public record ScheduleCommand(String crseNo, int year, String season, String doPlan) {
}
