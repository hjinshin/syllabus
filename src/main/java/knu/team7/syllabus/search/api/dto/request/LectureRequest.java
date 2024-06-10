package knu.team7.syllabus.search.api.dto.request;

import knu.team7.syllabus.core.type.DayType;
import lombok.Getter;

import java.util.List;

@Getter
public class LectureRequest {
    private int year;                         // 개설연도
    private String season;                    // 개설학기
    private String crseNo;                    // 과목코드
    private String sbjctNm;                   // 과목명
    private String subjctCd;                  // 강좌번호
    private String professor;                 // 교수명
    private String college;                   // 개설대학
    private String depart;                    // 개설학과
    private String lang;                      // 강의언어
    private String sbjctSection;              // 교과구분 ex) 교양, 전공
    private String lSect;                     // 대구분 ex) 첨성인핵심
    private String mSect;                     // 중구분
    private String sSect;                     // 소구분
    private List<DayDTO> days;                // 강의시간
    private EvaluationDTO evaluation;         // 평가방법
    private boolean isRemote;                 // 원격여부
    private boolean isHumanities;             // 인문교양
    private boolean isSdg;                    // SDG(지속가능발전목표)
    private boolean isFlipped;                // 플립드러닝강좌
    private boolean isNU;                     // 거점국립대원격강좌
    private boolean isDgKp;                   // 대구경북권역원격강좌
    private boolean isSu;                     // SU평가강좌
}

@Getter
class DayDTO {
    private DayType day; // 요일
    private String timeCode; // 시간코드
}

@Getter
class EvaluationDTO {
    private float attendance;    // 평가요소(출석)
    private float midExam;       // 평가요소(중간시험)
    private float finalExam;     // 평가요소(기말시험)
    private float assignment;    // 평가요소(과제)
    private float presentation;  // 평가요소(발표)
    private float debate;        // 평가요소(토론)
    private float safetyEdu;     // 평가요소(안전교육)
    private float etc;           // 평가요소(기타)
    private float total;         // 평가요소(총합)
}
