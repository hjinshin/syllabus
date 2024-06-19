package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity(name = "FetchSubjectCodeJpaEntity")
@Table(name = "subject")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubjectJpaEntity {
    @Id
    private String sbjetCd; // 강좌번호 ex) xxxx0001
    private String sbjetNm; // 과목명

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectJpaEntity that = (SubjectJpaEntity) o;
        return Objects.equals(sbjetCd, that.sbjetCd) && Objects.equals(sbjetNm, that.sbjetNm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sbjetCd, sbjetNm);
    }

    public void setSbjetNm(String sbjetNm) {
        this.sbjetNm = sbjetNm;
    }

    @Builder(toBuilder = true)
    public SubjectJpaEntity(String sbjetCd, String sbjetNm) {
        this.sbjetCd = sbjetCd;
        this.sbjetNm = sbjetNm;
    }
}
