package knu.team7.syllabus.infrastructure.adapter.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "professor")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfessorJpaEntity {
    @Id
    private String profNm;          // 담당교수
    private String profTel;         // 교수 연락처
    private String profEmail;       // 교수 이메일

}
