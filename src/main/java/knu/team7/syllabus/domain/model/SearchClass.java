package knu.team7.syllabus.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SearchClass implements Search{
    private String estblYear;
    private String estblSmstrSctcd;
    private String sbjetRelmCd;
    private String gubun;
    private String isApi;

    @Builder
    public SearchClass(String estblYear, String estblSmstrSctcd, String sbjetRelmCd, String gubun, String isApi) {
        this.estblYear = estblYear;
        this.estblSmstrSctcd = estblSmstrSctcd;
        this.sbjetRelmCd = sbjetRelmCd;
        this.gubun = gubun;
        this.isApi = isApi;
    }
}
