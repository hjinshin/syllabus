package knu.team7.syllabus.fetch.application.port.in.command;

import knu.team7.syllabus.fetch.domain.model.*;
import lombok.Builder;

import java.util.List;

@Builder
public record LectureCommand(int credit, int lecCr, int pracCr, String grade, String building, String room,
                             int capacity, String lang, boolean isRemote, String note, String preSbjet,
                             String postSbjet, String realLecTime,
                             boolean humanities, boolean sdg, boolean flipped, boolean nU,
                             boolean dgKp, boolean su,
                             List<LectureTime> lecTime, Course course, Professor professor,
                             Department department, SubjectSection subjectSection,
                             Evaluation evaluation) {
}
