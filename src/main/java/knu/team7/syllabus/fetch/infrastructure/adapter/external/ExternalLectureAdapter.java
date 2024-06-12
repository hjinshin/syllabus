package knu.team7.syllabus.fetch.infrastructure.adapter.external;

import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.core.util.ApiUtil;
import knu.team7.syllabus.core.util.GsonUtil;
import knu.team7.syllabus.fetch.application.port.in.command.external.Search;
import knu.team7.syllabus.fetch.application.port.in.command.external.SearchPayloadCommand;
import knu.team7.syllabus.fetch.application.port.out.FetchLecturePort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ExternalLectureAdapter implements FetchLecturePort {
    @Override
    public String requestLecture(Search search) throws Exception {
        return ApiUtil.post(
                Constants.CLASS_URL,
                GsonUtil.toJson(SearchPayloadCommand.builder()
                        .search(search)
                        .build()),
                null);
    }
}
