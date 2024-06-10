package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity(name = "FetchSubjectCodeJpaEntity")
@Table(name = "subject_code")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubjectCodeJpaEntity {
    @Id
    private String sbjetCd; // 과목코드 ex) xxxx0001
    private String sbjetNm; // 과목명

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectCodeJpaEntity that = (SubjectCodeJpaEntity) o;
        return Objects.equals(sbjetCd, that.sbjetCd) && Objects.equals(sbjetNm, that.sbjetNm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sbjetCd, sbjetNm);
    }

    @Builder
    public SubjectCodeJpaEntity(String sbjetCd, String sbjetNm) {
        this.sbjetCd = sbjetCd;
        this.sbjetNm = sbjetNm;
    }
}
