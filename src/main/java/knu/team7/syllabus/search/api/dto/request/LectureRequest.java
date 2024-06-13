package knu.team7.syllabus.search.api.dto.request;

import knu.team7.syllabus.search.domain.model.Day;
import knu.team7.syllabus.search.domain.model.Evaluation;
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
    private List<Day> days;                // 강의시간
    private Evaluation evaluation;         // 평가방법
    private boolean isRemote;                 // 원격여부
    private boolean isHumanities;             // 인문교양
    private boolean isSdg;                    // SDG(지속가능발전목표)
    private boolean isFlipped;                // 플립드러닝강좌
    private boolean isNU;                     // 거점국립대원격강좌
    private boolean isDgKp;                   // 대구경북권역원격강좌
    private boolean isSu;                     // SU평가강좌
}

