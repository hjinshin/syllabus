package knu.team7.syllabus.fetch.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Professor {
    private final Long id;
    private final String profNm;          // 담당교수
    private final String profTel;         // 교수 연락처
    private final String profEmail;       // 교수 이메일

    @Builder
    public Professor(Long id, String profNm, String profTel, String profEmail) {
        this.id = id;
        this.profNm = profNm;
        this.profTel = profTel;
        this.profEmail = profEmail;
    }
}
