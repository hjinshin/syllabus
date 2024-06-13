package knu.team7.syllabus.search.application.port.in.command;

import knu.team7.syllabus.search.domain.model.Day;
import knu.team7.syllabus.search.domain.model.Evaluation;
import lombok.Builder;

import java.util.List;

@Builder
public record LectureCommand(int year, String season, String crseNo, String sbjctNm, String subjctCd, String professor, String college, String depart, String lang, String sbjctSection, String lSect, String mSect, String sSect, List<Day> days, Evaluation evaluation, boolean isRemote, boolean isHumanities, boolean isSdg, boolean isFlipped, boolean isNU, boolean isDgKp, boolean isSu) {
    @Override
    public String toString() {
        return "LectureCommand{" +
                "year=" + year +
                ", season='" + season + '\'' +
                ", crseNo='" + crseNo + '\'' +
                ", sbjctNm='" + sbjctNm + '\'' +
                ", subjctCd='" + subjctCd + '\'' +
                ", professor='" + professor + '\'' +
                ", college='" + college + '\'' +
                ", depart='" + depart + '\'' +
                ", lang='" + lang + '\'' +
                ", sbjctSection='" + sbjctSection + '\'' +
                ", lSect='" + lSect + '\'' +
                ", mSect='" + mSect + '\'' +
                ", sSect='" + sSect + '\'' +
                ", days=" + days +
                ", evaluation=" + evaluation +
                ", isRemote=" + isRemote +
                ", isHumanities=" + isHumanities +
                ", isSdg=" + isSdg +
                ", isFlipped=" + isFlipped +
                ", isNU=" + isNU +
                ", isDgKp=" + isDgKp +
                ", isSu=" + isSu +
                '}';
    }
}
