package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.application.port.in.command.YearAndSeasonCommand;

public interface FetchYearAndSeasonUseCase {
    YearAndSeasonCommand fetchYearAndSeason() throws Exception;
}
