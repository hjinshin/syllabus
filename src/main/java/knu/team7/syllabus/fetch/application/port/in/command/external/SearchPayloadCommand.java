package knu.team7.syllabus.fetch.application.port.in.command.external;

import lombok.Builder;
@Builder
public record SearchPayloadCommand(Search search) {
}
