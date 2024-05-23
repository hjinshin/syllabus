package knu.team7.syllabus.infrastructure.adapter.dto.out;

import lombok.Builder;
@Builder
public record SearchPayloadCommand(Search search) {
}
