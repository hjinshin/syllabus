package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;
import knu.team7.syllabus.fetch.application.port.in.command.SyllabusCommand;
import knu.team7.syllabus.fetch.application.port.out.FetchSyllabusPort;
import knu.team7.syllabus.fetch.application.usecase.FetchSyllabusUseCase;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FetchSyllabusService implements FetchSyllabusUseCase {
    private final FetchSyllabusPort fetchSyllabusPort;

    @Override
    public List<List<SyllabusCommand>> fetchSyllabus(List<LectureCommand> lecList) throws Exception {
        List<List<SyllabusCommand>> list = new ArrayList<>();
        for (int i=0; i < lecList.size(); i++) {
            LectureCommand command = lecList.get(i);
            List<SyllabusCommand> syllabusCommandList = new ArrayList<>();
            syllabusCommandList.add(fetchSyllabus(command.estblYear(), command.estblSmstrSctcd(), command.crseNo(), "Kor"));
            syllabusCommandList.add(fetchSyllabus(command.estblYear(), command.estblSmstrSctcd(), command.crseNo(), "Eng"));
            list.add(syllabusCommandList);
            lecList.set(i, command.toBuilder()
                    .preSbjet(syllabusCommandList.get(0).preSbjet())
                    .postSbjet(syllabusCommandList.get(0).postSbjet())
                    .lang(syllabusCommandList.get(0).lang())
                    .build());
        }
        return list;
    }

    @Override
    public SyllabusCommand fetchSyllabus(String year, String season, String crseNo, String doPlan) throws Exception {
        return fetchSyllabusPort.fetchSyllabus(year, season, crseNo, doPlan);
    }
}
