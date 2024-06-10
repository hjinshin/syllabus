package knu.team7.syllabus.fetch.application.port.in.command;

import lombok.Builder;

@Builder
public record DepartmentCommand(String college, String depart) {
}
