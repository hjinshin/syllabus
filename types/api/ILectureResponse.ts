/**
 * 강의 목록
 */

export interface IEvaluationMethod {
  attendance: number; // 평가요소(출석)
  midExam: number; // 평가요소(중간시험)
  finalExam: number; // 평가요소(기말시험)
  assignment: number; // 평가요소(과제)
  presentation: number; // 평가요소(발표)
  debate: number; // 평가요소(토론)
  safetyEdu: number; // 평가요소(안전교육)
  etc: number; // 평가요소(기타)
  total: number; // 평가요소(총합)
}

export enum Day {
  MON = "월",
  TUE = "화",
  WED = "수",
  THU = "목",
  FRI = "금",
  SAT = "토",
  SUN = "일",
}

export interface ILectureTime {
  day: Day;
  timeCode: string;
}

export interface ILecture {
  year: number; // 개설연도
  season: string; // 개설학기
  sbjSection: string; // 교과구분
  lSct: string; // 대구분
  mSct: string; // 중구분
  sSct: string; // 소구분
  college: string; // 개설대학
  depart: string; // 개설학과
  grade: string; // 학년
  crseNo: string; // 과목코드
  sbjctNm: string; // 과목명
  sbjctCd: string; // 강좌번호
  realLecTime: string; // 강의시간
  lecTimes: ILectureTime[]; // 강의시간코드
  credit: number; // 학점
  lecCr: number; // 강의실
  pracCr: number; // 실습
  professor: string; // 교수명
  building: string; //강의실
  room: string; // 호실
  capacity: number; // 수강정원
  lang: string; // 강의언어
  isRemote: boolean; // 원격여부
  preSbjet: string; // 권장 선수 과목
  postSbjet: string; // 권장 후수 과목
  evaluation: IEvaluationMethod; // 평가방법
  isHumanities: boolean; // 인문교양
  isSdg: boolean; // SDG 교양
  isDgKp: boolean; // 대구경북권역원격강좌
  isSu: boolean; // SU 평가 강좌
  note: string; // 비고
}

export interface ILectureResponse {
  success: boolean;
  error: string;
  data: ILecture[];
}
