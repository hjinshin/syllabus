package knu.team7.syllabus.application.port.out.command;

import knu.team7.syllabus.domain.model.LectureTime;
import lombok.Builder;

import java.util.List;

/**
 * @param codeId            교과코드
 * @param estblYear         개설연도
 * @param estblSmstrSctnm   개설학기명
 * @param estblSmstrSctcd   개설학기코드
 * @param sbjetSctnm        교과구분
 * @param estblUnivNm       개설학과
 * @param estblDprtnNm      개설대학
 * @param estblGrade        학년
 * @param sbjetNm           과목명
 * @param sbjetCd           과목코드
 * @param crseNo            강좌번호
 * @param lssnsTimeInfo     강의시간
 * @param lssnsRealTimeInfo 실제강의시간
 * @param crdit             학점
 * @param thryTime          강의
 * @param prctsTime         실습
 * @param totalPrfssNm      교수명
 * @param lctrmInfo         강의실
 * @param rmnmCd            호실
 * @param attlcPrscpCnt     수강정원
 * @param doPlan            강의언어
 * @param expniSllbsYn      원격강좌
 * @param rmrk              비고
 */
@Builder
public record OutLectureCommand(String codeId, String estblYear, String estblSmstrSctnm, String estblSmstrSctcd, String sbjetSctnm,
                                String estblUnivNm, String estblDprtnNm, String estblGrade, String sbjetNm,
                                String sbjetCd, String crseNo, List<LectureTime> lssnsTimeInfo, String lssnsRealTimeInfo,
                                String crdit, String thryTime, String prctsTime, String totalPrfssNm,
                                String lctrmInfo, String rmnmCd, String attlcPrscpCnt, String doPlan,
                                String expniSllbsYn, String rmrk) {
}
