package knu.team7.syllabus.application.port.in.command;

import lombok.Builder;

@Builder
public record CourseCommand(String crseNo, String year, String season) {
}
