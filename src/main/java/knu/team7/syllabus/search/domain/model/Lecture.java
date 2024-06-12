package knu.team7.syllabus.search.domain.model;

import knu.team7.syllabus.fetch.domain.model.Evaluation;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class Lecture {
    private final int year;       // 개설연도
    private final String season;          // 개설학기
    private final String sbjSection;      // 교과구분 ex) 교양, 전공
    private final String lSct;            // 대구분 ex) 첨성인핵심
    private final String mSct;            // 중구분
    private final String sSct;            // 소구분
    private final String college;         // 개설대학
    private final String depart;          // 개설학과
    private final String grade;           // 학년
    private final String crseNo;          // 과목코드
    private final String sbjctNm;         // 과목명
    private final String subjctCd;        // 강좌번호
    private final String realLecTime;     // 실제강의사간
    private final List<LectureTime> lecTimes;  // 강의시간코드
    private final String credit;      // 학점
    private final int lecCr;          // 강의
    private final int pracCr;         // 실습
    private final String professor;      // 교수명
    private final String building;    // 강의실
    private final String room;        // 호실
    private final int capacity;       // 수강정원
    private final String lang;        // 강의언어
    private final boolean isRemote;   // 원격여부
    private final String preSbjet;    // 권장선수과목
    private final String postSbjet;   // 권장선수과목
    private final Evaluation evaluation; // 평가방법
    private final boolean isHumanities; //인문교양
    private final boolean isSdg;        //SDG(지속가능발전목표)
    private final boolean isFlipped;  //플립드러닝
    private final boolean isNU;       //거점국립대원격강좌
    private final boolean isDgKp;     //대구경북권역원격강좌
    private final boolean isSu;       // SU평가강좌
    private final String note;        // 비고

    @Builder
    public Lecture(int year, String season, String sbjSection, String lSct, String mSct, String sSct, String college, String depart, String grade, String crseNo, String sbjctNm, String subjctCd, String realLecTime, List<LectureTime> lecTimes, String credit, int lecCr, int pracCr, String professor, String building, String room, int capacity, String lang, boolean isRemote, String preSbjet, String postSbjet, Evaluation evaluation, boolean isHumanities, boolean isSdg, boolean isFlipped, boolean isNU, boolean isDgKp, boolean isSu, String note) {
        this.year = year;
        this.season = season;
        this.sbjSection = sbjSection;
        this.lSct = lSct;
        this.mSct = mSct;
        this.sSct = sSct;
        this.college = college;
        this.depart = depart;
        this.grade = grade;
        this.crseNo = crseNo;
        this.sbjctNm = sbjctNm;
        this.subjctCd = subjctCd;
        this.realLecTime = realLecTime;
        this.lecTimes = lecTimes;
        this.credit = credit;
        this.lecCr = lecCr;
        this.pracCr = pracCr;
        this.professor = professor;
        this.building = building;
        this.room = room;
        this.capacity = capacity;
        this.lang = lang;
        this.isRemote = isRemote;
        this.preSbjet = preSbjet;
        this.postSbjet = postSbjet;
        this.evaluation = evaluation;
        this.isHumanities = isHumanities;
        this.isSdg = isSdg;
        this.isFlipped = isFlipped;
        this.isNU = isNU;
        this.isDgKp = isDgKp;
        this.isSu = isSu;
        this.note = note;
    }
}
