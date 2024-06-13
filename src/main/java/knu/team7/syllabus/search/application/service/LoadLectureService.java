package knu.team7.syllabus.search.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.search.domain.model.Lecture;
import knu.team7.syllabus.search.application.port.in.command.LectureCommand;
import knu.team7.syllabus.search.application.port.out.LoadLecturePort;
import knu.team7.syllabus.search.application.usecase.LoadLectureUseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class LoadLectureService implements LoadLectureUseCase {
    private final LoadLecturePort loadLecturePort;
    @Override
    public List<Lecture> loadLectures(LectureCommand command) {
        return loadLecturePort.loadLectures(command);
    }
}
