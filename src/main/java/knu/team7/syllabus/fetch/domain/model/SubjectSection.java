package knu.team7.syllabus.fetch.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SubjectSection {
    private final String codeId;  // 교과코드
    private final String sbjetSctnm;  // 교과구분
    private final String lSct; // 대구분
    private final String mSct;  // 중구분
    private final String sSct;   // 소구분

    @Builder
    public SubjectSection(String codeId, String sbjetSctnm, String lSct, String mSct, String sSct) {
        this.codeId = codeId;
        this.sbjetSctnm = sbjetSctnm;
        this.lSct = lSct;
        this.mSct = mSct;
        this.sSct = sSct;
    }
}
