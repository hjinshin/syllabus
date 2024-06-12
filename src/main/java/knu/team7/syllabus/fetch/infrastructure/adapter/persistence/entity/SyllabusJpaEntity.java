package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity(name = "FetchSyllabusJpaEntity")
@Table(name = "syllabus", uniqueConstraints = {@UniqueConstraint(columnNames = {"doPlan", "course_id"})})
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
    private String doPlan;      // 작성언어

    @Column(length = 1000)
    private String profNm;          // 담당교수
    private String profTel;         // 교수 연락처
    private String profEmail;       // 교수 이메일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private CourseJpaEntity courseJpaEntity;    // 강좌번호

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SyllabusJpaEntity that = (SyllabusJpaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(crseGoal, that.crseGoal) && Objects.equals(eduGoal, that.eduGoal) && Objects.equals(summary, that.summary) && Objects.equals(textbook, that.textbook) && Objects.equals(evalMethd, that.evalMethd) && Objects.equals(intviTimeLoc, that.intviTimeLoc) && Objects.equals(refer, that.refer) && Objects.equals(doPlan, that.doPlan) && Objects.equals(profNm, that.profNm) && Objects.equals(profTel, that.profTel) && Objects.equals(profEmail, that.profEmail) && Objects.equals(courseJpaEntity, that.courseJpaEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, crseGoal, eduGoal, summary, textbook, evalMethd, intviTimeLoc, refer, doPlan, profNm, profTel, profEmail, courseJpaEntity);
    }

    @Builder(toBuilder = true)
    public SyllabusJpaEntity(Long id, String crseGoal, String eduGoal, String summary, String textbook, String evalMethd, String intviTimeLoc, String refer, String doPlan, String profNm, String profTel, String profEmail, CourseJpaEntity courseJpaEntity) {
        this.id = id;
        this.crseGoal = crseGoal;
        this.eduGoal = eduGoal;
        this.summary = summary;
        this.textbook = textbook;
        this.evalMethd = evalMethd;
        this.intviTimeLoc = intviTimeLoc;
        this.refer = refer;
        this.doPlan = doPlan;
        this.profNm = profNm;
        this.profTel = profTel;
        this.profEmail = profEmail;
        this.courseJpaEntity = courseJpaEntity;
    }
}
