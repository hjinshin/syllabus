package knu.team7.syllabus.application.port.in.command;

import lombok.Builder;

@Builder
public record ListCommand(String section, String sCodeId, String sCodeNm,
                          String mCodeId, String mCodeNm,
                          String lCodeId, String lCodeNm) {
}
