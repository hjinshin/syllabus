package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.application.port.in.command.YearAndSeasonCommand;

public interface FetchYearAndSeasonPort {
    YearAndSeasonCommand fetchYearAndSeason() throws Exception;
}
