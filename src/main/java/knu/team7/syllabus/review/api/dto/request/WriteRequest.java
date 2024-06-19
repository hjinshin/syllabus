package knu.team7.syllabus.review.api.dto.request;

import lombok.Getter;

@Getter
public class WriteRequest {
    private int year;                         // 개설연도
    private String season;                    // 개설학기
    private String crseNo;                    // 과목코드
    private String profNm;                    // 교수명
    private float rating;                       // 별점
    private String contents;                  // 강의평
}
