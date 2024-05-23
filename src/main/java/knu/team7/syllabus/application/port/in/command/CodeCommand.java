package knu.team7.syllabus.application.port.in.command;

import lombok.Builder;

@Builder
public record CodeCommand(String codeId, String codeNm, String upperCodeId, String upperCodeNm) {
}
