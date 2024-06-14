package knu.team7.syllabus.review.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ReadResponse {
    private final String crseNo;         // 교과번호
    private final String profNm;          // 교수명
    private final float avgRating;        // 평균평점
    private final List<ReviewResponse> reviews;   //  리뷰

    @Builder

    public ReadResponse(String crseNo, String profNm, float avgRating, List<ReviewResponse> reviews) {
        this.crseNo = crseNo;
        this.profNm = profNm;
        this.avgRating = avgRating;
        this.reviews = reviews;
    }
}
