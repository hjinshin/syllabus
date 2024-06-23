/**
 * 강의 목록
 */
export interface ILectureRequest {
  year: number; // 개설연도
  season: string; // 개설학기
  crseNo: string; // 과목코드
  sbjctNm: string; // 과목명
  subjctCd: string; // 강좌번호
  professor: string; // 교수명
  college: string; // 개설대학
  depart: string; // 개설학과
  lang: string; // 강의언어
  sbjctSection: string; // 교과구분 ex) 교양, 전공
  lSect: string; // 대구분 ex) 첨성인핵심
  mSect: string; // 중구분
  sSect: string; // 소구분
  evaluation: {
    attendance: number; // 평가요소(출석) 사용 안할경우 -1
    midExam: number; // 평가요소(중간시험) 사용 안할경우 -1
    finalExam: number; // 평가요소(기말시험) 사용 안할경우 -1
    assignment: number; // 평가요소(과제) 사용 안할경우 -1
    presentation: number; // 평가요소(발표) 사용 안할경우 -1
    debate: number; // 평가요소(토론) 사용 안할경우 -1
    safetyEdu: number; // 평가요소(안전교육) 사용 안할경우 -1
    etc: number; // 평가요소(기타) 사용 안할경우 -1
    total: number; // 평가요소(총합) 사용 안할경우 -1
  };
  isRemote: boolean; // 원격여부 true로만 검색 가능
  isHumanities: boolean; // 인문교양 true로만 검색 가능
  isSdg: boolean; // SDG(지속가능발전목표) true로만 검색 가능
  isFlipped: boolean; // 플립드러닝강좌 true로만 검색 가능
  isNU: boolean; // 거점국립대원격강좌 true로만 검색 가능
  isDgKp: boolean; // 대구경북권역원격강좌 true로만 검색 가능
  isSu: boolean; // SU평가강좌 true로만 검색 가능
}
