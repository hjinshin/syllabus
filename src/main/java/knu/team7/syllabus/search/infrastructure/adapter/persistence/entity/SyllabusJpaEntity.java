package knu.team7.syllabus.search.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "syllabus")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SyllabusJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String crseGoal;       // 강의목표
    @Column(columnDefinition = "TEXT")
    private String eduGoal;        // 교육목표
    @Column(columnDefinition = "TEXT")
    private String summary;        // 강의개요
    @Column(columnDefinition = "TEXT")
    private String textbook;       // 교재 및 참고문헌 직접입력
    @Column(columnDefinition = "TEXT")
    private String evalMethd;      // 평가방법
    private String intviTimeLoc;   // 상담장소/시간
    @Column(columnDefinition = "TEXT")
    private String refer;           // 수강 참고사항

    @OneToOne
    @JoinColumn(name = "course")
    private CourseJpaEntity courseJpaEntity;    // 강좌번호

    @Builder
    public SyllabusJpaEntity(String crseGoal, String eduGoal, String summary, String textbook, String evalMethd, String intviTimeLoc, String refer, CourseJpaEntity courseJpaEntity) {
        this.crseGoal = crseGoal;
        this.eduGoal = eduGoal;
        this.summary = summary;
        this.textbook = textbook;
        this.evalMethd = evalMethd;
        this.intviTimeLoc = intviTimeLoc;
        this.refer = refer;
        this.courseJpaEntity = courseJpaEntity;
    }
}
