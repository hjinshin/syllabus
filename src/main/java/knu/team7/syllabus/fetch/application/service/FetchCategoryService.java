package knu.team7.syllabus.fetch.application.service;

import knu.team7.syllabus.core.Constants;
import knu.team7.syllabus.core.annotation.UseCase;
import knu.team7.syllabus.fetch.application.port.out.FetchCategoryPort;
import knu.team7.syllabus.fetch.application.usecase.FetchCategoryUseCase;
import knu.team7.syllabus.fetch.domain.model.Category;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FetchCategoryService implements FetchCategoryUseCase {

    private final FetchCategoryPort fetchLecturePort;
    @Override
    public List<Category> fetchGECategory() throws Exception {
        List<Category> list = new ArrayList<>();
        for (String key : Constants.GECATEGORY_KEYS) {
            list.addAll(fetchLecturePort.fetchGECategory(key));
        }
        return list;
    }

    @Override
    public List<Category> fetchOtherCategory() {
        List<Category> list = new ArrayList<>();
        for (String[] codes : Constants.SUBCODES) {
            list.add(Category.builder()
                    .category(codes[1])
                    .lCodeId(codes[0])
                    .build()
            );
        }

        return list;
    }


}
