package knu.team7.syllabus.fetch.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Department {
    private final Long id;
    private final String college;    // 개설대학
    private final String depart;    // 개설학과

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(college, that.college) && Objects.equals(depart, that.depart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(college, depart);
    }

    @Builder
    public Department(Long id, String college, String depart) {
        this.id = id;
        this.college = college;
        this.depart = depart;
    }
}
