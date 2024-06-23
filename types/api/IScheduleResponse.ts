/**
 * 주별강의
 */

export interface ISchedule {
  lssnsGoalCntns: string; // 수업목표 및 학습내용
  lssnsMethd: string; // 수업방법 및 매체
  rsrchCntns: string; // 과제 및 연구문제
  weekSn: number; // 주차(신뢰도 낮음)
  weekNote: string; // 비고
  doPlan: string; // 작성언어
}

export interface IScheduleResponse {
  success: boolean;
  error: string;
  data: ISchedule[];
}
