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
    private String sbjetSctcd2;
    private String gubun;
    private String isApi;

    @Builder
    public SearchClass(String estblYear, String estblSmstrSctcd, String sbjetRelmCd, String sbjetSctcd2, String gubun, String isApi) {
        this.estblYear = estblYear;
        this.estblSmstrSctcd = estblSmstrSctcd;
        this.sbjetRelmCd = sbjetRelmCd;
        this.sbjetSctcd2 = sbjetSctcd2;
        this.gubun = gubun;
        this.isApi = isApi;
    }

}
