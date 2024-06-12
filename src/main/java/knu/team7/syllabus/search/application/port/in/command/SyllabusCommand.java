package knu.team7.syllabus.search.application.port.in.command;

import lombok.Builder;

@Builder
public record SyllabusCommand(String doPlan, int year, String season, String crseNo) {
}
