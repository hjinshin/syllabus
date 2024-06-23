/**
 * 특정 강의목록 요청
 */
export interface ILectureSelectRequest {
  year: number; // 개설연도
  season: string; // 개설학기
  cresNo: string[]; // 과목코드 리스트
}
