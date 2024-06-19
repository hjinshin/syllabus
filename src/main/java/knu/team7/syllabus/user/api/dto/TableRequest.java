package knu.team7.syllabus.user.api.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TableRequest {
    private int year;
    private String season;
    private List<String> crseNos;
}
