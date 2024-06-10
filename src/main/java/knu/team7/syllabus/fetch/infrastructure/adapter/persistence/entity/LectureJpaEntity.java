package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "FetchLectureJpaEntity")
@Table(name = "lecture")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int credit;         // 학점
    private int lecCr;          // 강의
    private int pracCr;         // 실습
    private String grade;       // 학년
    private String building;    // 강의실
    private String room;        // 호실
    private int capacity;       // 수강정원
    private String lang;        // 강의언어
    private boolean isRemote;   // 원격여부
    @Column(columnDefinition = "TEXT")
    private String note;        // 비고
    @Column(columnDefinition = "TEXT")
    private String preSbjet;    // 권장선수과목
    @Column(columnDefinition = "TEXT")
    private String postSbjet;   // 권장선수과목
    private String realLecTime; // 실제강의시간
    private boolean isHumanities; //인문교양
    private boolean isSdg;        //SDG(지속가능발전목표)
    private boolean isFlipped;  //플립드러닝
    private boolean isNU;       //거점국립대원격강좌
    private boolean isDgKp;     //대구경북권역원격강좌
    private boolean isSu;       // SU평가강좌

    @OneToOne
    @JoinColumn(name = "course")
    private CourseJpaEntity courseJpaEntity;    // 강좌번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor")
    private ProfessorJpaEntity professorJpaEntity;    // 교수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department")
    private DepartmentJpaEntity departmentJpaEntity;    // 개설학과

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_section")
    private SubjectSectionJpaEntity subjectSectionJpaEntity;  // 교과구분

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LectureTimeJpaEntity> lectureTimes = new ArrayList<>();    // 강의시간

    @OneToOne
    @JoinColumn(name = "evaluation")
    private EvaluationJpaEntity evaluation;   // 평가방법

    public void addLectureTimeEntity(LectureTimeJpaEntity entity) {
        if (entity == null) {
            return;
        }
        lectureTimes.add(entity);
        entity.seLectureJpaEntity(this);
    }

    @Builder
    public LectureJpaEntity(int credit, int lecCr, int pracCr, String grade, String building, String room, int capacity, String lang, boolean isRemote, String note, String preSbjet, String postSbjet, String realLecTime, boolean isHumanities, boolean isSdg, boolean isFlipped, boolean isNU, boolean isDgKp, boolean isSu, CourseJpaEntity courseJpaEntity, ProfessorJpaEntity professorJpaEntity, DepartmentJpaEntity departmentJpaEntity, SubjectSectionJpaEntity subjectSectionJpaEntity, List<LectureTimeJpaEntity> lectureTimes, EvaluationJpaEntity evaluation) {
        this.credit = credit;
        this.lecCr = lecCr;
        this.pracCr = pracCr;
        this.grade = grade;
        this.building = building;
        this.room = room;
        this.capacity = capacity;
        this.lang = lang;
        this.isRemote = isRemote;
        this.note = note;
        this.preSbjet = preSbjet;
        this.postSbjet = postSbjet;
        this.realLecTime = realLecTime;
        this.isHumanities = isHumanities;
        this.isSdg = isSdg;
        this.isFlipped = isFlipped;
        this.isNU = isNU;
        this.isDgKp = isDgKp;
        this.isSu = isSu;
        this.courseJpaEntity = courseJpaEntity;
        this.professorJpaEntity = professorJpaEntity;
        this.departmentJpaEntity = departmentJpaEntity;
        this.subjectSectionJpaEntity = subjectSectionJpaEntity;
        this.lectureTimes = lectureTimes;
        this.evaluation = evaluation;
    }
}
