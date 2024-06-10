package knu.team7.syllabus.fetch.application.port.in.command;

import knu.team7.syllabus.fetch.domain.model.SubjectCode;
import lombok.Builder;

@Builder
public record CourseCommand(String crseNo, String year, String season, SubjectCode subjectCode) {
}
