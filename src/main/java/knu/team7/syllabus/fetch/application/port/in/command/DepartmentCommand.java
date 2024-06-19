package knu.team7.syllabus.fetch.application.port.in.command;

import lombok.Builder;

import java.util.Objects;

@Builder
public record DepartmentCommand(String college, String depart) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentCommand that = (DepartmentCommand) o;
        return Objects.equals(college, that.college) && Objects.equals(depart, that.depart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(college, depart);
    }
}
