package knu.team7.syllabus.fetch.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SubjectCode {
    private final String sbjetCd; // 과목코드 ex) xxxx0001
    private final String sbjetNm; // 과목명

    @Builder
    public SubjectCode(String sbjetCd, String sbjetNm) {
        this.sbjetCd = sbjetCd;
        this.sbjetNm = sbjetNm;
    }
}
