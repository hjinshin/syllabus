package knu.team7.syllabus.fetch.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Subject {
    private final String sbjetCd; // 과목코드 ex) xxxx0001
    private final String sbjetNm; // 과목명

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject that = (Subject) o;
        return Objects.equals(sbjetCd, that.sbjetCd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sbjetCd);
    }

    @Builder
    public Subject(String sbjetCd, String sbjetNm) {
        this.sbjetCd = sbjetCd;
        this.sbjetNm = sbjetNm;
    }
}
