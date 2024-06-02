package knu.team7.syllabus.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TempSyllabus {
    private String crseNo;                // 강좌번호
    private String estblYear;             // 개설연도
    private String estblDprtnNm;          // 개설대학
    private String estblSmstrSctnm;       // 개설학기
    private String sbjetSctnm;            // 교과목 구분
    private String sbjetSctcd;            // 교과목 코드
    private String sbjetNm;               // 교과목명
    private String sbjetDvnno;            // 강좌 번호
    private String sbjetCd;               // 교과목코드
    private String crditSyste;            // 학점 0-0-0
    private String lssnsTimeInfo;         // 강의시간
    private String lctrmCd;               // 강의실
    private String rprsnStfnoNm;          // 담당교수
    private String oriCntacMtlno;         // 교수 연락처
    private String oriCntacEmail;         // 교수 이메일
    private String oriLctreGoalCntns;     // 강의목표
    private String oriLctreGoalCntnsCom;  // 교육목표
    private String oriSmmryPrpsCntns;     // 강의개요
    private String oriTchmtRefLtrtuCntns; // 교재 및 참고문헌 직접입력
    private String oriTaskEvltnMethdCntns;// 평가방법
    private String oriRcmmdPlrSbjetInfo;  // 권장선수과목
    private String oriRcmmdSbstsSbjetInfo;// 권장후수과목
    private String oriIntviTimeInfo;      // 상담장소/시간
    private String oriEvltnRate1;         // 평가요소(출석)
    private String oriEvltnRate2;         // 평가요소(중간시험)
    private String oriEvltnRate3;         // 평가요소(기말시험)
    private String oriEvltnRate4;         // 평가요소(과제)
    private String oriEvltnRate5;         // 평가요소(발표)
    private String oriEvltnRate6;         // 평가요소(토론)
    private String oriEvltnRate8;         // 평가요소(안전교육)
    private String oriEvltnRate9;         // 평가요소(기타)
    private String oriGradsTotalScre;     // 평가요소(총합)
    private String oriAttlcRefMtterCntns; // 수강 참고사항

    public TempSyllabus() {
    }

    @Builder
    public TempSyllabus(String crseNo, String estblYear, String estblDprtnNm, String estblSmstrSctnm, String sbjetSctnm, String sbjetSctcd, String sbjetNm, String sbjetDvnno, String sbjetCd, String crditSyste, String lssnsTimeInfo, String lctrmCd, String oriLctreGoalCntns, String oriLctreGoalCntnsCom, String oriSmmryPrpsCntns, String oriTchmtRefLtrtuCntns, String oriTaskEvltnMethdCntns, String oriRcmmdPlrSbjetInfo, String oriRcmmdSbstsSbjetInfo, String oriIntviTimeInfo, String oriEvltnRate1, String oriEvltnRate2, String oriEvltnRate3, String oriEvltnRate4, String oriEvltnRate5, String oriEvltnRate6, String oriEvltnRate8, String oriEvltnRate9, String oriGradsTotalScre, String oriAttlcRefMtterCntns, String rprsnStfnoNm, String oriCntacMtlno, String oriCntacEmail) {
        this.crseNo = crseNo;
        this.estblYear = estblYear;
        this.estblDprtnNm = estblDprtnNm;
        this.estblSmstrSctnm = estblSmstrSctnm;
        this.sbjetSctnm = sbjetSctnm;
        this.sbjetSctcd = sbjetSctcd;
        this.sbjetNm = sbjetNm;
        this.sbjetDvnno = sbjetDvnno;
        this.sbjetCd = sbjetCd;
        this.crditSyste = crditSyste;
        this.lssnsTimeInfo = lssnsTimeInfo;
        this.lctrmCd = lctrmCd;
        this.oriLctreGoalCntns = oriLctreGoalCntns;
        this.oriLctreGoalCntnsCom = oriLctreGoalCntnsCom;
        this.oriSmmryPrpsCntns = oriSmmryPrpsCntns;
        this.oriTchmtRefLtrtuCntns = oriTchmtRefLtrtuCntns;
        this.oriTaskEvltnMethdCntns = oriTaskEvltnMethdCntns;
        this.oriRcmmdPlrSbjetInfo = oriRcmmdPlrSbjetInfo;
        this.oriRcmmdSbstsSbjetInfo = oriRcmmdSbstsSbjetInfo;
        this.oriIntviTimeInfo = oriIntviTimeInfo;
        this.oriEvltnRate1 = oriEvltnRate1;
        this.oriEvltnRate2 = oriEvltnRate2;
        this.oriEvltnRate3 = oriEvltnRate3;
        this.oriEvltnRate4 = oriEvltnRate4;
        this.oriEvltnRate5 = oriEvltnRate5;
        this.oriEvltnRate6 = oriEvltnRate6;
        this.oriEvltnRate8 = oriEvltnRate8;
        this.oriEvltnRate9 = oriEvltnRate9;
        this.oriGradsTotalScre = oriGradsTotalScre;
        this.oriAttlcRefMtterCntns = oriAttlcRefMtterCntns;
        this.rprsnStfnoNm = rprsnStfnoNm;
        this.oriCntacMtlno = oriCntacMtlno;
        this.oriCntacEmail = oriCntacEmail;
    }
}
