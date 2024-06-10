package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity(name = "FetchSubjectSectionJpaEntity")
@Table(name = "subject_section")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubjectSectionJpaEntity {
    @Id
    private String codeId;  // 교과코드
    private String sbjetSctnm;  // 교과구분
    private String lSct; // 대구분
    private String mSct;  // 중구분
    private String sSct;   // 소구분

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectSectionJpaEntity that = (SubjectSectionJpaEntity) o;
        return Objects.equals(codeId, that.codeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeId);
    }

    @Builder
    public SubjectSectionJpaEntity(String codeId, String sbjetSctnm, String lSct, String mSct, String sSct) {
        this.codeId = codeId;
        this.sbjetSctnm = sbjetSctnm;
        this.lSct = lSct;
        this.mSct = mSct;
        this.sSct = sSct;
    }
}
