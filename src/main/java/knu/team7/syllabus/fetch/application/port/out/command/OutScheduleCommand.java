package knu.team7.syllabus.fetch.application.port.out.command;

import lombok.Builder;

/**
 * @param lssnsGoalCntns 수업목표 및 학습내용
 * @param lssnsMethd     수업방법 및 매체
 * @param rsrchCntns     과제 및 연구문제
 * @param weekSn         주차(1부터 있지는 않아서 신뢰도 낮음)
 * @param weekNote       비고
 */
@Builder
public record OutScheduleCommand(String lssnsGoalCntns, String lssnsMethd, String rsrchCntns, String weekSn,
                                 String weekNote) {
}
