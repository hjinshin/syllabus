package knu.team7.syllabus.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subject_section")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubjectSectionJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sbjetSctnm;  // 교과구분
    private String lSct; // 대구분
    private String mSct;  // 중구분
    private String sSct;   // 소구분

    @Builder
    public SubjectSectionJpaEntity(String sbjetSctnm, String lSct, String mSct, String sSct) {
        this.sbjetSctnm = sbjetSctnm;
        this.lSct = lSct;
        this.mSct = mSct;
        this.sSct = sSct;
    }
}
