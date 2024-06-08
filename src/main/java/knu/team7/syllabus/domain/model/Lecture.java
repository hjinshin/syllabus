package knu.team7.syllabus.domain.model;

import lombok.Builder;
import lombok.Getter;

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
    private final Course course;
    private final Professor professor;
    private final Department department;
    private final SubjectCode subjectCode;
    private final SubjectSection subjectSection;
    private final Evaluation evaluation;

    @Builder
    public Lecture(Long id, int credit, int lecCr, int pracCr, String grade, String building, String room, int capacity, String lang, boolean isRemote, String note, String preSbjet, String postSbjet, String realLecTime, Course course, Professor professor, Department department, SubjectCode subjectCode, SubjectSection subjectSection, Evaluation evaluation) {
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
        this.course = course;
        this.professor = professor;
        this.department = department;
        this.subjectCode = subjectCode;
        this.subjectSection = subjectSection;
        this.evaluation = evaluation;
    }
}
