/**
 * 주별강의
 */
export interface IScheduleRequest {
  crseNo: string; // 과목코드
  year: number; // 개설연도
  season: string; // 개설학기
  doPlan: string; // 작성언어
}
