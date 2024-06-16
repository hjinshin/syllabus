package knu.team7.syllabus.fetch.application.port.in.command;

import lombok.Builder;

@Builder
public record YearAndSeasonCommand(int year, String season) {
}
