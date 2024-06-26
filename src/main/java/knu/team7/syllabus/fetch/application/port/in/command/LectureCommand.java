package knu.team7.syllabus.fetch.application.port.in.command;

import knu.team7.syllabus.fetch.domain.model.LectureTime;
import lombok.Builder;

import java.util.List;

/**
 * @param sectCd            구분코드
 * @param estblYear         개설연도
 * @param estblSmstrSctnm   개설학기명
 * @param estblSmstrSctcd   개설학기코드
 * @param sbjetSctnm        교과구분
 * @param estblUnivNm       개설대학
 * @param estblDprtnNm      개설학과
 * @param estblGrade        학년
 * @param sbjetNm           과목명
 * @param sbjetCd           강의번호
 * @param crseNo            과목코드
 * @param lssnsTimeInfo     강의시간
 * @param lssnsRealTimeInfo 실제강의시간
 * @param crdit             학점
 * @param thryTime          강의
 * @param prctsTime         실습
 * @param totalPrfssNm      교수명
 * @param lctrmInfo         강의실
 * @param rmnmCd            호실
 * @param attlcPrscpCnt     수강정원
 * @param expniSllbsYn      원격강좌
 * @param rmrk              비고
 * @param humanities        인문교양
 * @param sdg               SDG(지속가능발전목표)
 * @param flipped           플립드러닝
 * @param nU                거점국립대원격강좌
 * @param dgKp              대구경북권역원격강좌
 * @param su                SU평가강좌
 * @param preSbjet          권장선수과목
 * @param postSbjet         권장후수과목
 * @param lang              강의언어
 */
@Builder(toBuilder = true)
public record LectureCommand(String sectCd, String estblYear, String estblSmstrSctnm, String estblSmstrSctcd, String sbjetSctnm,
                                String estblUnivNm, String estblDprtnNm, String estblGrade, String sbjetNm,
                                String sbjetCd, String crseNo, List<LectureTime> lssnsTimeInfo, String lssnsRealTimeInfo,
                                String crdit, String thryTime, String prctsTime, String totalPrfssNm,
                                String lctrmInfo, String rmnmCd, String attlcPrscpCnt,
                                String expniSllbsYn, String rmrk, String preSbjet, String postSbjet,
                                boolean humanities, boolean sdg, boolean flipped, boolean nU,
                                boolean dgKp, boolean su, String lang) {
}
