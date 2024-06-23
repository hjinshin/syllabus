/**
 * 세부강의계획서
 */
export interface SyllabusResponse {
  success: boolean;
  error: string;
  data: {
    crseNo: string; // 과목코드
    crseGoal: string; // 강의목표
    eduGoal: string; // 교육목표
    summary: string; // 강의개요
    textbook: string; // 교재 및 참고문헌(직접입력)
    evalmethd: string; // 평가방법
    intviTimeLoc: string; // 상담장소/시간
    refer: string; // 수강 참고사항
    profNm: string; // 교수명
    profTel: string; // 교수 전화번호
    profEmail: string; // 교수 이메일
  };
}
