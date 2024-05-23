package knu.team7.syllabus.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SearchSyllabus implements Search{
    private String estblYear;
    private String estblSmstrSctcd;
    private String sbjetCd;
    private String sbjetDvnno;
    private String lctreLnggeSctcd;
    private String isApi;

    @Builder
    public SearchSyllabus(String estblYear, String estblSmstrSctcd, String sbjetCd, String sbjetDvnno, String lctreLnggeSctcd, String isApi) {
        this.estblYear = estblYear;
        this.estblSmstrSctcd = estblSmstrSctcd;
        this.sbjetCd = sbjetCd;
        this.sbjetDvnno = sbjetDvnno;
        this.lctreLnggeSctcd = lctreLnggeSctcd;
        this.isApi = isApi;
    }
}
