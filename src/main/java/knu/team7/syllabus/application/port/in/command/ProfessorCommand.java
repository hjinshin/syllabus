package knu.team7.syllabus.application.port.in.command;

import lombok.Builder;

import java.util.Objects;

@Builder
public record ProfessorCommand(String profNm, String profTel, String profEmail) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessorCommand that = (ProfessorCommand) o;
        return Objects.equals(profNm, that.profNm) &&
                Objects.equals(profTel, that.profTel) &&
                Objects.equals(profEmail, that.profEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profNm, profTel, profEmail);
    }
}
