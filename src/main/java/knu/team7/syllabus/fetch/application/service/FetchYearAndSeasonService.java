package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.application.port.in.command.YearAndSeasonCommand;
import knu.team7.syllabus.fetch.application.port.out.FetchYearAndSeasonPort;
import knu.team7.syllabus.fetch.application.usecase.FetchYearAndSeasonUseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FetchYearAndSeasonService implements FetchYearAndSeasonUseCase {
    private final FetchYearAndSeasonPort fetchYearAndSeasonPort;
    @Override
    public YearAndSeasonCommand fetchYearAndSeason() throws Exception {
        return fetchYearAndSeasonPort.fetchYearAndSeason();
    }
}
