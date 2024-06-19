package knu.team7.syllabus.review.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Review {
    private final int year;
    private final String season;
    private final String crseNo;
    private final String profNm;
    private final float rating;
    private final String contents;
    private final LocalDateTime createdAt;

    @Builder
    public Review(int year, String season, String crseNo, String profNm, float rating, String contents, LocalDateTime createdAt) {
        this.year = year;
        this.season = season;
        this.crseNo = crseNo;
        this.profNm = profNm;
        this.rating = rating;
        this.contents = contents;
        this.createdAt = createdAt;
    }
}
