package knu.team7.syllabus.fetch.application.port.in.command;

import lombok.Builder;

import java.util.Objects;

/**
 * @param crseNo       과목코드
 * @param year         개설연도
 * @param season       개설학기
 * @param crseGoal     강의목표
 * @param eduGoal      교육목표
 * @param summary      강의개요
 * @param textbook     교재 및 참고문헌 직접입력
 * @param evalMethd    평가방법
 * @param intviTimeLoc 상담장소/시간
 * @param refer        수강 참고사항
 * @param lang         강의언어
 * @param profNm       담당교수
 * @param profTel      교수 연락처
 * @param profEmail    교수 이메일
 * @param preSbjet     권장선수과목
 * @param postSbjet    권장선수과목
 * @param attendance   평가요소(출석)
 * @param midExam      평가요소(중간시험)
 * @param finalExam    평가요소(기말시험)
 * @param assignment   평가요소(과제)
 * @param presentation 평가요소(발표)
 * @param debate       평가요소(토론)
 * @param safetyEdu    평가요소(안전교육)
 * @param etc          평가요소(기타)
 * @param total        평가요소(총합)
 * @param doPlan       작성언어
 */
@Builder
public record SyllabusCommand(String crseNo, String year, String season, String crseGoal, String eduGoal, String summary,
                              String textbook, String evalMethd, String intviTimeLoc, String refer, String lang, String profNm,
                              String profTel, String profEmail, String preSbjet, String postSbjet, String attendance,
                              String midExam, String finalExam, String assignment, String presentation, String debate,
                              String safetyEdu, String etc, String total, String doPlan) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SyllabusCommand command = (SyllabusCommand) o;
        return Objects.equals(crseNo, command.crseNo) && Objects.equals(year, command.year) && Objects.equals(season, command.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crseNo, year, season);
    }
}
