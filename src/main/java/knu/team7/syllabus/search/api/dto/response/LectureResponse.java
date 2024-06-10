package knu.team7.syllabus.search.api.dto.response;

import knu.team7.syllabus.core.type.DayType;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class LectureResponse {
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
    private final List<LectureTimeDTO> lecTimes;  // 강의시간코드
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
    private final EvaluationDTO evaluation; // 평가방법
    private final boolean isHumanities; //인문교양
    private final boolean isSdg;        //SDG(지속가능발전목표)
    private final boolean isFlipped;  //플립드러닝
    private final boolean isNU;       //거점국립대원격강좌
    private final boolean isDgKp;     //대구경북권역원격강좌
    private final boolean isSu;       // SU평가강좌
    private final String note;        // 비고

    @Builder
    public LectureResponse(int year, String season, String sbjSection, String lSct, String mSct, String sSct, String college, String depart, String grade, String crseNo, String sbjctNm, String subjctCd, String realLecTime, List<LectureTimeDTO> lecTimes, String credit, int lecCr, int pracCr, String professor, String building, String room, int capacity, String lang, boolean isRemote, String preSbjet, String postSbjet, EvaluationDTO evaluation, boolean isHumanities, boolean isSdg, boolean isFlipped, boolean isNU, boolean isDgKp, boolean isSu, String note) {
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

@Getter
class LectureTimeDTO {
    private final DayType day;        // 요일 ex) MON("월"),TUE("화"),WED("수"),THU("목"),FRI("금"), SAT("토"),SUN("일");
    private final String timeCode;

    @Builder
    public LectureTimeDTO(DayType day, String timeCode) {
        this.day = day;
        this.timeCode = timeCode;
    }
}

@Getter
class EvaluationDTO {
    private final float attendance;    // 평가요소(출석)
    private final float midExam;       // 평가요소(중간시험)
    private final float finalExam;     // 평가요소(기말시험)
    private final float assignment;    // 평가요소(과제)
    private final float presentation;  // 평가요소(발표)
    private final float debate;        // 평가요소(토론)
    private final float safetyEdu;     // 평가요소(안전교육)
    private final float etc;           // 평가요소(기타)
    private final float total;         // 평가요소(총합)

    @Builder
    public EvaluationDTO(float attendance, float midExam, float finalExam, float assignment, float presentation, float debate, float safetyEdu, float etc, float total) {
        this.attendance = attendance;
        this.midExam = midExam;
        this.finalExam = finalExam;
        this.assignment = assignment;
        this.presentation = presentation;
        this.debate = debate;
        this.safetyEdu = safetyEdu;
        this.etc = etc;
        this.total = total;
    }
}
