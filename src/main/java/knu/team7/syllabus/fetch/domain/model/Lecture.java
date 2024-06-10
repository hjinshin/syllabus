package knu.team7.syllabus.fetch.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Lecture {
    private final Long id;
    private final int credit;         // 학점
    private final int lecCr;          // 강의
    private final int pracCr;         // 실습
    private final String grade;          // 학년
    private final String building;    // 강의실
    private final String room;        // 호실
    private final int capacity;       // 수강정원
    private final String lang;        // 강의언어
    private final boolean isRemote;   // 원격여부
    private final String note;        // 비고
    private final String preSbjet;    // 권장선수과목
    private final String postSbjet;   // 권장선수과목
    private final String realLecTime; // 실제강의시간

    private final List<LectureTime> lectimes;   // 강의시간 과목코드
    private final Course course;
    private final Professor professor;
    private final Department department;
    private final SubjectSection subjectSection;
    private final Evaluation evaluation;
    private final boolean isHumanities;
    private final boolean isSdg;
    private final boolean isFlipped;
    private final boolean isNU;
    private final boolean isDgKp;
    private final boolean isSu;


    @Builder
    public Lecture(Long id, int credit, int lecCr, int pracCr, String grade, String building, String room, int capacity, String lang, boolean isRemote, String note, String preSbjet, String postSbjet, String realLecTime, List<LectureTime> lectimes, Course course, Professor professor, Department department, SubjectSection subjectSection, Evaluation evaluation, boolean isHumanities, boolean isSdg, boolean isFlipped, boolean isNU, boolean isDgKp, boolean isSu) {
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
        this.lectimes = lectimes;
        this.course = course;
        this.professor = professor;
        this.department = department;
        this.subjectSection = subjectSection;
        this.evaluation = evaluation;
        this.isHumanities = isHumanities;
        this.isSdg = isSdg;
        this.isFlipped = isFlipped;
        this.isNU = isNU;
        this.isDgKp = isDgKp;
        this.isSu = isSu;
    }
}
