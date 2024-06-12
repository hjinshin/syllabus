package knu.team7.syllabus.fetch.application.port.in.command.external;

import lombok.Builder;

@Builder
public record SearchSyllabusCommand(String estblYear, String estblSmstrSctcd, String sbjetCd, String sbjetDvnno, String lctreLnggeSctcd, String isApi, String doPlan) implements Search{

}
