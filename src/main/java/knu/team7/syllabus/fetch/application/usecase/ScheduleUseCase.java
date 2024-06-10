package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.out.command.OutScheduleCommand;

import java.util.List;

public interface ScheduleUseCase {
    List<OutScheduleCommand> getSchedule(String year, String season, String subjectCode, String doPlan) throws Exception;
}
