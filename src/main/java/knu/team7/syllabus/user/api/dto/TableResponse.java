package knu.team7.syllabus.user.api.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TableResponse {
    private final List<Long> lectureIdList;

    public TableResponse(List<Long> lectureIdList) {
        this.lectureIdList = lectureIdList;
    }
}
