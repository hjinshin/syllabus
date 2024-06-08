package knu.team7.syllabus.application.service;

import knu.team7.syllabus.application.port.in.command.LectureCommand;
import knu.team7.syllabus.application.port.out.CreateLecturePort;
import knu.team7.syllabus.application.usecase.CreateLectureUseCase;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.domain.model.Lecture;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CreateLectureService implements CreateLectureUseCase {
    private final CreateLecturePort createLecturePort;
    @Override
    public List<Lecture> createLecture(List<LectureCommand> list) {
        return createLecturePort.createLecture(list);
    }
}
