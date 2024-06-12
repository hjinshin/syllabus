package knu.team7.syllabus.fetch.application.port.in.command;

import lombok.Builder;

import java.util.Objects;

@Builder
public record SubjectCommand(String sbjetCd, String sbjetNm) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectCommand that = (SubjectCommand) o;
        return Objects.equals(sbjetCd, that.sbjetCd) && Objects.equals(sbjetNm, that.sbjetNm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sbjetCd, sbjetNm);
    }
}
