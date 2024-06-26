package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateLecturePort;
import knu.team7.syllabus.fetch.application.usecase.CreateLectureUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CreateLectureService implements CreateLectureUseCase {
    private final CreateLecturePort createLecturePort;

    @Override
    public void createLecture(List<LectureCommand> list) {
        createLecturePort.createLecture(list);
    }
}
