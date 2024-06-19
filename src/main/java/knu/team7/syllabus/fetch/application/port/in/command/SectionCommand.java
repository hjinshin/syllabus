package knu.team7.syllabus.fetch.application.port.in.command;

import lombok.Builder;

import java.util.Objects;

@Builder
public record SectionCommand(String codeId, String sctNm, String lSct, String mSct, String sSct) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SectionCommand that = (SectionCommand) o;
        return Objects.equals(codeId, that.codeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeId);
    }
}
