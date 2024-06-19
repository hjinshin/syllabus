package knu.team7.syllabus.user.api.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private final boolean success;
    private final String error;
    private final T data;

    @Builder
    public ApiResponse(boolean success, String error, T data) {
        this.success = success;
        this.error = error;
        this.data = data;
    }
}