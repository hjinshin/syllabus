package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Entity(name = "FetchProfessorJpaEntity")
@Table(name = "professor")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfessorJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String profNm;          // 담당교수
    private String profTel;         // 교수 연락처
    private String profEmail;       // 교수 이메일

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfessorJpaEntity that)) return false;
        return Objects.equals(profNm, that.profNm) && Objects.equals(profTel, that.profTel) &&
                Objects.equals(profEmail, that.profEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profNm, profTel, profEmail);
    }

    @Builder
    public ProfessorJpaEntity(Long id, String profNm, String profTel, String profEmail) {
        this.id = id;
        this.profNm = profNm;
        this.profTel = profTel;
        this.profEmail = profEmail;
    }
}
