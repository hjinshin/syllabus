package knu.team7.syllabus.fetch.application.usecase;

import knu.team7.syllabus.fetch.domain.model.Category;

import java.util.List;

public interface FetchCategoryUseCase {
    List<Category> fetchGECategory() throws Exception;
    List<Category> fetchOtherCategory();
}
