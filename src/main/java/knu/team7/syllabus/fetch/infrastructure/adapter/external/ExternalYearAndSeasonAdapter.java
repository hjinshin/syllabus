package knu.team7.syllabus.fetch.infrastructure.adapter.external;

import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.core.util.ApiUtil;
import knu.team7.syllabus.core.util.GsonUtil;
import knu.team7.syllabus.core.util.ParseUtil;
import knu.team7.syllabus.fetch.application.port.in.command.YearAndSeasonCommand;
import knu.team7.syllabus.fetch.application.port.in.command.external.Search;
import knu.team7.syllabus.fetch.application.port.in.command.external.SearchPayloadCommand;
import knu.team7.syllabus.fetch.application.port.in.command.external.SearchYearAndSeasonCommand;
import knu.team7.syllabus.fetch.application.port.out.FetchYearAndSeasonPort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ExternalYearAndSeasonAdapter implements FetchYearAndSeasonPort {
    @Override
    public YearAndSeasonCommand fetchYearAndSeason() throws Exception {
        Search search = SearchYearAndSeasonCommand.builder()
                .eventId(Constants.YEARANDSEASON_KEY)
                .mngtYn("N")
                .isApi("Y")
                .build();
        String response = requestYearAndSeason(search);
        return ParseUtil.parsingYearSeasonData(response);
    }

    private String requestYearAndSeason(Search search) throws Exception {
        return ApiUtil.post(
                Constants.YEAR_SEASON_URL,
                GsonUtil.toJson(SearchPayloadCommand.builder()
                        .search(search)
                        .build()),
                null);
    }
}
