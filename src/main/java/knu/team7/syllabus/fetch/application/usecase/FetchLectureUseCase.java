package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;
import knu.team7.syllabus.fetch.domain.model.Category;

import java.util.List;

public interface FetchLectureUseCase {
    List<LectureCommand> fetchGELecture(List<Category> idList, int year, String season) throws Exception;
    List<LectureCommand> fetchOtherLecture(List<Category> idList, int year, String season) throws Exception;
}
