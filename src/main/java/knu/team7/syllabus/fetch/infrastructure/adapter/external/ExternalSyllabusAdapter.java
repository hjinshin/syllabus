package knu.team7.syllabus.fetch.infrastructure.adapter.external;

import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.core.util.ApiUtil;
import knu.team7.syllabus.core.util.GsonUtil;
import knu.team7.syllabus.core.util.ParseUtil;
import knu.team7.syllabus.fetch.application.port.in.command.external.Search;
import knu.team7.syllabus.fetch.application.port.in.command.external.SearchPayloadCommand;
import knu.team7.syllabus.fetch.application.port.in.command.external.SearchSyllabusCommand;
import knu.team7.syllabus.fetch.application.port.in.command.SyllabusCommand;
import knu.team7.syllabus.fetch.application.port.out.FetchSyllabusPort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ExternalSyllabusAdapter implements FetchSyllabusPort {

    @Override
    public SyllabusCommand fetchSyllabus(String year, String season, String crseNo, String doPlan) throws Exception {
        String[] code = crseNo.split("-");
        Search search = SearchSyllabusCommand.builder()
                .estblYear(year)
                .estblSmstrSctcd(season)
                .sbjetCd(code[0])
                .sbjetDvnno(code[1])
                .lctreLnggeSctcd(season.replace("CMBS", "STCU"))
                .isApi("Y")
                .doPlan(doPlan)
                .build();
        String response = requestSyllabus(search);
        return ParseUtil.parsingSyllabusData(response, doPlan);
    }


    private String requestSyllabus(Search search) throws Exception {
        return ApiUtil.post(
                Constants.SYLLABUS_URL,
                GsonUtil.toJson(SearchPayloadCommand.builder()
                        .search(search)
                        .build()),
                null);
    }
}
