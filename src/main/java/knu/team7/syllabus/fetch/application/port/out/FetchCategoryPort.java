package knu.team7.syllabus.fetch.application.port.out;

import knu.team7.syllabus.fetch.domain.model.Category;

import java.util.List;

public interface FetchCategoryPort {
    List<Category> fetchGECategory(String key) throws Exception;
}
