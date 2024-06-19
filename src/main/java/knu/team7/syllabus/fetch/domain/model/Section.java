package knu.team7.syllabus.fetch.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Section {
    private final String codeId;  // 교과코드
    private final String sctNm;  // 교과구분
    private final String lSct; // 대구분
    private final String mSct;  // 중구분
    private final String sSct;   // 소구분

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section that = (Section) o;
        return Objects.equals(codeId, that.codeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeId);
    }

    @Builder
    public Section(String codeId, String sctNm, String lSct, String mSct, String sSct) {
        this.codeId = codeId;
        this.sctNm = sctNm;
        this.lSct = lSct;
        this.mSct = mSct;
        this.sSct = sSct;
    }
}
