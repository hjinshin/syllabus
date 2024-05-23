package knu.team7.core;

public class Constants {
    public static final String[] SEASONCODES = {
            "CMBS001400001", // 1학기
            "CMBS001400004", // 여름학기
            "CMBS001400002", // 2학기
            "CMBS001400003"  // 겨울학기
    };
    public static final String[] GECODES = {
            "STCU001000005", // 첨성인기초
//            "STCU001000006", // 첨성인핵심     첨성인핵심의 경우 깊이가 1개 더 있어서 문제 발생
            "STCU001000007", // 첨성인일반
            "STCU001000028", // 첨성인소양
            "STCU001100027", // 첨성인핵심(인문/사회)
            "STCU001100028"  // 첨성인핵심(자연과학)
    };

    public static final String[] SUBCODES = {
            "STCU000800002",  // 전공기초
            "STCU000800003",  // 기초공통
            "STCU000800004",  // 전공
            "STCU000800007",  // 교직
            "STCU000800010",  // 연계전공
            "STCU000800011",  // 전공선택
            "STCU000800012",  // 전공필수
            "STCU000800023",  // 융합전공
            "STCU000800024",  // 전공심화
            "STCU000800025",  // 일반선택
            "STCU000800026",  // 공학전공
            "STCU000800027"   // 전공기반
    };

    public static final String LISTURL = "https://knuin.knu.ac.kr/public/cmmnn/cmmbs/code/selectListCode";
    public static final String CLASSURL = "https://knuin.knu.ac.kr/public/web/stddm/lsspr/syllabus/lectPlnInqr/selectListLectPlnInqr";
    public static final String[] GELIST_KEYS = {"STCU0011", "STCU0012"};
    public static final String SUBLIST_KEY = "STCU0008";
}
