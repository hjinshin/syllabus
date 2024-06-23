/**
 * 유저 테이블 저장
 */
export interface ITableRequest {
  year: number; // 개설연도
  season: string; // 개설학기
  cresNo: string[]; // 과목코드 리스트
}
