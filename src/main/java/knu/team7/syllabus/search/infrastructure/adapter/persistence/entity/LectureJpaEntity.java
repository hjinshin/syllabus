package knu.team7.syllabus.search.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "SearchLectureJpaEntity")
@Table(name = "lecture", uniqueConstraints = {@UniqueConstraint(columnNames = "course_id")})
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
    private String postSbjet;   // 권장후수과목
    @Column(columnDefinition = "TEXT")
    private String realLecTime; // 실제강의시간
    private String profNm;      // 교수이름
    private boolean isHumanities; //인문교양
    private boolean isSdg;        //SDG(지속가능발전목표)
    private boolean isFlipped;  //플립드러닝
    private boolean isNU;       //거점국립대원격강좌
    private boolean isDgKp;     //대구경북권역원격강좌
    private boolean isSu;       // SU평가강좌

    @OneToOne
    @JoinColumn(name = "course_id")
    private CourseJpaEntity courseJpaEntity;    // 과목코드

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private DepartmentJpaEntity departmentJpaEntity;    // 개설학과

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private SectionJpaEntity sectionJpaEntity;  // 교과구분

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<LectureTimeJpaEntity> lectureTimes = new ArrayList<>();    // 강의시간

    public void addLectureTimeEntity(LectureTimeJpaEntity entity) {
        if (entity == null) {
            return;
        }
        lectureTimes.add(entity);
        entity.setLectureJpaEntity(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectureJpaEntity that = (LectureJpaEntity) o;
        return Objects.equals(courseJpaEntity.getId(), that.courseJpaEntity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseJpaEntity);
    }

    @Builder(toBuilder = true)
    public LectureJpaEntity(Long id, int credit, int lecCr, int pracCr, String grade, String building, String room, int capacity, String lang, boolean isRemote, String note, String preSbjet, String postSbjet, String realLecTime, String profNm, boolean isHumanities, boolean isSdg, boolean isFlipped, boolean isNU, boolean isDgKp, boolean isSu, CourseJpaEntity courseJpaEntity, DepartmentJpaEntity departmentJpaEntity, SectionJpaEntity sectionJpaEntity, List<LectureTimeJpaEntity> lectureTimes) {
        this.id = id;
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
        this.profNm = profNm;
        this.isHumanities = isHumanities;
        this.isSdg = isSdg;
        this.isFlipped = isFlipped;
        this.isNU = isNU;
        this.isDgKp = isDgKp;
        this.isSu = isSu;
        this.courseJpaEntity = courseJpaEntity;
        this.departmentJpaEntity = departmentJpaEntity;
        this.sectionJpaEntity = sectionJpaEntity;
        for (LectureTimeJpaEntity lectureTime : lectureTimes) {
            addLectureTimeEntity(lectureTime);
        }
    }

    @Override
    public String toString() {
        return "LectureJpaEntity{" +
                "id=" + id +
                ", credit=" + credit +
                ", lecCr=" + lecCr +
                ", pracCr=" + pracCr +
                ", grade='" + grade + '\'' +
                ", building='" + building + '\'' +
                ", room='" + room + '\'' +
                ", capacity=" + capacity +
                ", lang='" + lang + '\'' +
                ", isRemote=" + isRemote +
                ", note='" + note + '\'' +
                ", preSbjet='" + preSbjet + '\'' +
                ", postSbjet='" + postSbjet + '\'' +
                ", realLecTime='" + realLecTime + '\'' +
                ", profNm='" + profNm + '\'' +
                ", isHumanities=" + isHumanities +
                ", isSdg=" + isSdg +
                ", isFlipped=" + isFlipped +
                ", isNU=" + isNU +
                ", isDgKp=" + isDgKp +
                ", isSu=" + isSu +
                ", courseJpaEntity=" + courseJpaEntity +
                ", departmentJpaEntity=" + departmentJpaEntity +
                ", sectionJpaEntity=" + sectionJpaEntity +
                ", lectureTimes=" + lectureTimes +
                '}';
    }
}
