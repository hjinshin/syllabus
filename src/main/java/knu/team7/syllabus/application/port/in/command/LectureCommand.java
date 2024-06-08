package knu.team7.syllabus.application.port.in.command;

import knu.team7.syllabus.domain.model.*;
import lombok.Builder;

@Builder
public record LectureCommand(int credit, int lecCr, int pracCr, String grade, String building, String room,
                             int capacity, String lang, boolean isRemote, String note, String preSbjet,
                             String postSbjet, String realLecTime, Course course, Professor professor,
                             Department department, SubjectSection subjectSection,
                             Evaluation evaluation) {
}
