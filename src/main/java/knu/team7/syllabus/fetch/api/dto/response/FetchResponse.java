package knu.team7.syllabus.fetch.api.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FetchResponse {
    private final boolean success;
    private final String note;

    @Builder
    public FetchResponse(boolean success, String note) {
        this.success = success;
        this.note = note;
    }
}
