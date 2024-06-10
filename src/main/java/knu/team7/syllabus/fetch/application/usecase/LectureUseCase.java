package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.ListCommand;
import knu.team7.syllabus.fetch.application.port.out.command.OutLectureCommand;

import java.util.List;

public interface LectureUseCase {
    List<OutLectureCommand> getGELectureList(List<ListCommand> idList, String year, String season) throws Exception;
    List<OutLectureCommand> getOtherLectureList(List<ListCommand> idList, String year, String season) throws Exception;

}
