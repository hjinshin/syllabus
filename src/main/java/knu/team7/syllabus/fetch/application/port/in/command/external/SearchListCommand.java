package knu.team7.syllabus.fetch.application.port.in.command.external;

import lombok.Builder;

@Builder
public record SearchListCommand(String key) implements Search{
}
