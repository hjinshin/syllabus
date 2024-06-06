package knu.team7.syllabus.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String crseNo;  // 강좌번호
    private int year;           // 개설연도
    private String season;      // 개설학기

    @Builder
    public CourseJpaEntity(String crseNo, int year, String season) {
        this.crseNo = crseNo;
        this.year = year;
        this.season = season;
    }
}
