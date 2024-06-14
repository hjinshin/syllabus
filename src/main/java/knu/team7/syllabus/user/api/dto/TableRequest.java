package knu.team7.syllabus.user.api.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TableRequest {
    int year;
    String season;
    List<String> crseNos;
}
