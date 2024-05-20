package knu.team7.syllabus.domain.model;

import lombok.Builder;

public class SearchPayload {
    private Search search;

    @Builder
    public SearchPayload(Search search) {
        this.search = search;
    }
}
