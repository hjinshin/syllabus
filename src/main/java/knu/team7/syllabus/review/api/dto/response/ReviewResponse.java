package knu.team7.syllabus.review.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewResponse {
    private final String crseNo;
    private final String profNm;
    private final int year;                   // 수강연도
    private final String season;              // 수강학기
    private final float rating;               // 평점
    private final String contents;            // 내용
    private final int like;                   // 좋아요 수
    private final LocalDateTime createdAt;    // 작성일

    @Builder
    public ReviewResponse(String crseNo, String profNm, int year, String season, float rating, String contents, int like, LocalDateTime createdAt) {
        this.crseNo = crseNo;
        this.profNm = profNm;
        this.year = year;
        this.season = season;
        this.rating = rating;
        this.contents = contents;
        this.like = like;
        this.createdAt = createdAt;
    }
}
