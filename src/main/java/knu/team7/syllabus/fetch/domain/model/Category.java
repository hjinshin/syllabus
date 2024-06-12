package knu.team7.syllabus.fetch.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Category {
    private final String category;
    private final String sCodeId;
    private final String sCodeNm;
    private final String mCodeId;
    private final String mCodeNm;
    private final String lCodeId;
    private final String lCodeNm;

    @Builder
    public Category(String category, String sCodeId, String sCodeNm, String mCodeId, String mCodeNm, String lCodeId, String lCodeNm) {
        this.category = category;
        this.sCodeId = sCodeId;
        this.sCodeNm = sCodeNm;
        this.mCodeId = mCodeId;
        this.mCodeNm = mCodeNm;
        this.lCodeId = lCodeId;
        this.lCodeNm = lCodeNm;
    }
}
