package knu.team7.syllabus.review.application.port.in;

import lombok.Builder;

@Builder
public record WriteCommand(String userId, int year, String season, String crseNo, String profNm, float rating, String contents) {
}
