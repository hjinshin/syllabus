package knu.team7.syllabus.domain.model;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Department {
    private final Long id;
    private final String college;    // 개설대학
    private final String depart;    // 개설학과

    @Builder
    public Department(Long id, String college, String depart) {
        this.id = id;
        this.college = college;
        this.depart = depart;
    }
}
