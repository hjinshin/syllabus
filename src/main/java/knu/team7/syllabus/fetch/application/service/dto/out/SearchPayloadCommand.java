package knu.team7.syllabus.fetch.application.service.dto.out;

import lombok.Builder;
@Builder
public record SearchPayloadCommand(Search search) {
}
