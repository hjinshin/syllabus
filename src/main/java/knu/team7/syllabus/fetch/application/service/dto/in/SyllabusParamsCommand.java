package knu.team7.syllabus.fetch.application.service.dto.in;

import lombok.Builder;

@Builder
public record SyllabusParamsCommand(String year, String season, String crseNo) {
}
