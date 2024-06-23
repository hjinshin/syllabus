/**
 * 세부강의계획서
 */

export interface SyllabusRequest {
  doPlan: string; // 작성 언어
  year: number; // 개설연도
  season: string; // 개설학기
  crseNo: string; // 과목코드
}
