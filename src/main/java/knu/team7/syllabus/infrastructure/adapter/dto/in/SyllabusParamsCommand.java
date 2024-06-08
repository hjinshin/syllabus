package knu.team7.syllabus.infrastructure.adapter.dto.in;

import lombok.Builder;

@Builder
public record SyllabusParamsCommand(String year, String season, String crseNo) {
}
