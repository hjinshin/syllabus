package knu.team7.syllabus.fetch.application.port.in.command.external;

import lombok.Builder;

@Builder
public record SearchYearAndSeasonCommand(String eventId, String mngtYn, String isApi) implements Search {
}
