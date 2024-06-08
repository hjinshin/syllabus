package knu.team7.syllabus.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subject_code")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubjectCodeJpaEntity {
    @Id
    private String sbjetCd; // 과목코드 ex) xxxx0001
    private String sbjetNm; // 과목명



    @Builder
    public SubjectCodeJpaEntity(String sbjetCd, String sbjetNm) {
        this.sbjetCd = sbjetCd;
        this.sbjetNm = sbjetNm;
    }
}
