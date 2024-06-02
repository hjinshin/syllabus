package knu.team7.syllabus.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TempLecture {
    private String estblYear;       // 개설연도
    private String estblSmstrSctnm; // 개설학기명
    private String estblSmstrSctcd; // 개설학기코드
    private String sbjetSctnm;      // 교과구분
    private String estblUnivNm;     // 개설학과
    private String estblDprtnNm;    // 개설대학
    private String estblGrade;      // 학년
    private String sbjetNm;         // 과목명
    private String sbjetCd;         // 과목코드
    private String crseNo;          // 강좌코드
    private String lssnsTimeInfo;   // 강의시간
    private String lssnsRealTimeInfo;//실제강의시간
    private String crdit;           // 학점
    private String thryTime;        // 강의
    private String prctsTime;       // 실습
    private String totalPrfssNm;    // 교수명
    private String lctrmInfo;       // 강의실
    private String rmnmCd;          // 호실
    private String attlcPrscpCnt;   // 수강정원
    private String doPlan;          // 강의언어
    private String expniSllbsYn;    // 원격강좌
    private String rmrk;            // 비고


    public TempLecture() {
    }

    @Builder
    public TempLecture(String estblYear, String estblSmstrSctnm, String estblSmstrSctcd, String sbjetSctnm, String estblUnivNm, String estblDprtnNm, String estblGrade, String sbjetNm, String sbjetCd, String crseNo, String lssnsTimeInfo, String lssnsRealTimeInfo, String crdit, String thryTime, String prctsTime, String totalPrfssNm, String lctrmInfo, String rmnmCd, String attlcPrscpCnt, String doPlan, String expniSllbsYn, String rmrk) {
        this.estblYear = estblYear;
        this.estblSmstrSctnm = estblSmstrSctnm;
        this.estblSmstrSctcd = estblSmstrSctcd;
        this.sbjetSctnm = sbjetSctnm;
        this.estblUnivNm = estblUnivNm;
        this.estblDprtnNm = estblDprtnNm;
        this.estblGrade = estblGrade;
        this.sbjetNm = sbjetNm;
        this.sbjetCd = sbjetCd;
        this.crseNo = crseNo;
        this.lssnsTimeInfo = lssnsTimeInfo;
        this.lssnsRealTimeInfo = lssnsRealTimeInfo;
        this.crdit = crdit;
        this.thryTime = thryTime;
        this.prctsTime = prctsTime;
        this.totalPrfssNm = totalPrfssNm;
        this.lctrmInfo = lctrmInfo;
        this.rmnmCd = rmnmCd;
        this.attlcPrscpCnt = attlcPrscpCnt;
        this.doPlan = doPlan;
        this.expniSllbsYn = expniSllbsYn;
        this.rmrk = rmrk;
    }
}
