package knu.team7.syllabus.fetch.application.service.dto.out;

import lombok.Builder;

@Builder
public record SearchSyllabusCommand(String estblYear, String estblSmstrSctcd, String sbjetCd, String sbjetDvnno, String lctreLnggeSctcd, String isApi, String doPlan) implements Search{

}
