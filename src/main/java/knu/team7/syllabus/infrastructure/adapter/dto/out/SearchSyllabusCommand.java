package knu.team7.syllabus.infrastructure.adapter.dto.out;

import lombok.Builder;

@Builder
public record SearchSyllabusCommand(String estblYear, String estblSmstrSctcd, String sbjetCd, String sbjetDvnno, String lctreLnggeSctcd, String isApi) implements Search{

}
