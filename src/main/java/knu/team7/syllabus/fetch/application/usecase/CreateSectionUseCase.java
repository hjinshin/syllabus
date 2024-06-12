package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.domain.model.Category;
import knu.team7.syllabus.fetch.domain.model.Section;

import java.util.List;

public interface CreateSectionUseCase {
    List<Section> createSection(List<Category> list);
}
