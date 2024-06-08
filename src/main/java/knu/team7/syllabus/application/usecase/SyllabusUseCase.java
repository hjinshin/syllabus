package knu.team7.syllabus.application.usecase;

import knu.team7.syllabus.infrastructure.adapter.dto.in.SyllabusParamsCommand;
import knu.team7.syllabus.domain.model.Syllabus;

import java.util.List;

public interface SyllabusUseCase {
    List<Syllabus> getSyllabusList(List<SyllabusParamsCommand> commandList) throws Exception;
    Syllabus getSyllabus(String year, String season, String subjectCode) throws Exception;

}
