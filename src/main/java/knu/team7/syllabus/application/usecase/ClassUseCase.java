package knu.team7.syllabus.application.usecase;

import knu.team7.syllabus.application.port.in.command.CodeCommand;
import knu.team7.syllabus.domain.model.TempLecture;
import knu.team7.syllabus.infrastructure.adapter.dto.out.Search;

import java.util.List;

public interface ClassUseCase {
    List<TempLecture> getClassList(List<CodeCommand> codeCommandList, String year, String season) throws Exception;
    List<TempLecture> getClassList(String[] codeList, String year, String season) throws Exception;
    Search getGEClass(String code, String year, String season);
    Search getOtherClass(String code, String year, String season);
}
