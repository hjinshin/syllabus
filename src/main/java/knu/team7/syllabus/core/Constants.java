package knu.team7.syllabus.core;

import knu.team7.syllabus.core.type.DayType;

import java.util.HashMap;
import java.util.Map;

public class Constants {
    public static final Map<Character, DayType> DAY_TYPE = new HashMap<>() {{
        put('월', DayType.MON); put('화', DayType.TUE); put('수', DayType.WED); put('목', DayType.THU); put('금', DayType.FRI); put('토', DayType.SAT); put('일', DayType.SUN);
    }};
    public static final String LIST_URL = "https://knuin.knu.ac.kr/public/cmmnn/cmmbs/code/selectListCode";
    public static final String CLASS_URL = "https://knuin.knu.ac.kr/public/web/stddm/lsspr/syllabus/lectPlnInqr/selectListLectPlnInqr";
    public static final String SYLLABUS_URL = "https://knuin.knu.ac.kr/public/web/stddm/lsspr/syllabus/lectPlnInputDtl/selectListLectPlnInputDtl";
    public static final String SCHEDULE_URL = "https://knuin.knu.ac.kr/public/web/stddm/lsspr/syllabus/lectPlnInputDtl/selectListLectPlnInputDtlWekCntns";

    public static final Map<String, String> SEASONCODES = new HashMap<>(){{
        put("1학기", "CMBS001400001"); // 1학기
        put("여름학기", "CMBS001400004"); // 여름학기
        put("2학기", "CMBS001400002"); // 2학기
        put("겨울학기", "CMBS001400003");  // 겨울학기
    }};

    public static final String[][] GECATEGORYCODES = {
            {"STCU001000005", "첨성인기초", ""}, // 첨성인기초
            {"STCU001000007", "첨성인일반", ""}, // 첨성인일반
            {"STCU001000028", "첨성인소양", ""} // 첨성인소양
    };
    public static final String[][] GECATEGORYCORECODES = {
            {"STCU001100027", "첨성인핵심", "인문/사회"}, // 첨성인핵심(인문/사회)
            {"STCU001100028", "첨성인핵심", "자연과학"}  // 첨성인핵심(자연과학)
    };
    public static final String[] CHUMSUNGINCORE = {"STCU001000006", "첨성인핵심"};


    public static final String GESUBCODES = "STCU000800001";  // 교양
    public static final String[][] SUBCODES = {
//            {"STCU000800001", "교양"},  // 교양
            {"STCU000800002", "전공기초"},  // 전공기초
            {"STCU000800003", "기초공통"},  // 기초공통
            {"STCU000800004", "전공"},  // 전공
            {"STCU000800007", "교직"},  // 교직
            {"STCU000800010", "연계전공"},  // 연계전공
            {"STCU000800011", "전공선택"},  // 전공선택
            {"STCU000800012", "전공필수"},  // 전공필수
            {"STCU000800023", "융합전공"},  // 융합전공
            {"STCU000800024", "전공심화"},  // 전공심화
            {"STCU000800025", "일반선택"},  // 일반선택
            {"STCU000800026", "공학전공"},  // 공학전공
            {"STCU000800027", "전공기반"},  // 전공기반
            {"STCU000800028", "기본소양"}   // 기본소양
    };

    public static final String[] GECATEGORY_KEYS = {"STCU0011", "STCU0012"};
    public static final String SUBLIST_KEY = "STCU0008";
}
