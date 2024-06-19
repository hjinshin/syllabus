package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.core.util.ParseUtil;
import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;
import knu.team7.syllabus.fetch.application.port.in.command.external.Search;
import knu.team7.syllabus.fetch.application.port.in.command.external.SearchClassCommand;
import knu.team7.syllabus.fetch.application.port.out.FetchLecturePort;
import knu.team7.syllabus.fetch.application.usecase.FetchLectureUseCase;
import knu.team7.syllabus.fetch.domain.model.Category;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FetchLectureService implements FetchLectureUseCase {
    private final FetchLecturePort fetchLecturePort;
    @Override
    public List<LectureCommand> fetchGELecture(List<Category> idList, int year, String season) throws Exception {
        List<LectureCommand> list = new ArrayList<>();
        for (Category category : idList) {
            String code = ParseUtil.getCode(category);
            Search search = getGELecture(code, year, Constants.SEASONCODES.get(season));
            String response = fetchLecturePort.requestLecture(search);
            list.addAll(ParseUtil.parsingLectureData(response, code));
        }
        return list;
    }

    @Override
    public List<LectureCommand> fetchOtherLecture(List<Category> idList, int year, String season) throws Exception {
        List<LectureCommand> list = new ArrayList<>();
        for (Category category : idList) {
            String code = ParseUtil.getCode(category);
            Search search = getOtherLecture(code, year, Constants.SEASONCODES.get(season));
            String response = fetchLecturePort.requestLecture(search);
            list.addAll(ParseUtil.parsingLectureData(response, code));
        }
        return list;
    }
    public Search getGELecture(String code, int year, String season) {
        return SearchClassCommand.builder()
                .estblYear(String.valueOf(year))
                .estblSmstrSctcd(season)
                .sbjetRelmCd(code)
                .sbjetSctcd2(Constants.GESUBCODES)
                .gubun("01")
                .isApi("Y")
                .build();
    }

    public Search getOtherLecture(String code, int year, String season) {
        return SearchClassCommand.builder()
                .estblYear(String.valueOf(year))
                .estblSmstrSctcd(season)
                .sbjetSctcd2(code)
                .gubun("01")
                .isApi("Y")
                .build();
    }

}
