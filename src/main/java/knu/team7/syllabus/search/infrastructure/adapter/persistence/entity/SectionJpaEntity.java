package knu.team7.syllabus.search.infrastructure.adapter.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity(name = "SearchSubjectSectionJpaEntity")
@Table(name = "section")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SectionJpaEntity {
    @Id
    private String codeId;  // 구분코드
    private String sctNm;  // 교과구분
    private String lSct; // 대구분
    private String mSct;  // 중구분
    private String sSct;   // 소구분

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionJpaEntity that = (SectionJpaEntity) o;
        return Objects.equals(codeId, that.codeId) && Objects.equals(sctNm, that.sctNm) && Objects.equals(lSct, that.lSct) && Objects.equals(mSct, that.mSct) && Objects.equals(sSct, that.sSct);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeId, sctNm, lSct, mSct, sSct);
    }

    @Builder(toBuilder = true)
    public SectionJpaEntity(String codeId, String sctNm, String lSct, String mSct, String sSct) {
        this.codeId = codeId;
        this.sctNm = sctNm;
        this.lSct = lSct;
        this.mSct = mSct;
        this.sSct = sSct;
    }

    @Override
    public String toString() {
        return "SectionJpaEntity{" +
                "codeId='" + codeId + '\'' +
                ", sctNm='" + sctNm + '\'' +
                ", lSct='" + lSct + '\'' +
                ", mSct='" + mSct + '\'' +
                ", sSct='" + sSct + '\'' +
                '}';
    }
}
