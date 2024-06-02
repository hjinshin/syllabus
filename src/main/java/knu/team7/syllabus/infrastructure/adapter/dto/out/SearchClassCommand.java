package knu.team7.syllabus.infrastructure.adapter.dto.out;

import lombok.Builder;

@Builder
public record SearchClassCommand(String estblYear, String estblSmstrSctcd, String sbjetRelmCd, String sbjetSctcd2, String gubun, String isApi) implements Search{

}