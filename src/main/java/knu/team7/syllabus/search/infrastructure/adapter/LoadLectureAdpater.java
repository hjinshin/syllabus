package knu.team7.syllabus.search.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.search.application.port.in.command.LectureCommand;
import knu.team7.syllabus.search.application.port.out.LoadLecturePort;
import knu.team7.syllabus.search.domain.model.Evaluation;
import knu.team7.syllabus.search.domain.model.Lecture;
import knu.team7.syllabus.search.domain.model.LectureTime;
import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.EvaluationJpaEntity;
import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.LectureJpaEntity;
import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.LectureTimeJpaEntity;
import knu.team7.syllabus.search.infrastructure.adapter.persistence.repository.LectureRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadLectureAdpater implements LoadLecturePort {
    private final LectureRepository lectureRepository;
    @Override
    public List<Lecture> loadLectures(LectureCommand command) {
        List<LectureJpaEntity> entities = lectureRepository.findAllLectures(command);

        return lectureListMapper(entities);
    }

    private List<Lecture> lectureListMapper(List<LectureJpaEntity> entities) {
        return entities.stream().map(
                entity -> Lecture.builder()
                        .year(entity.getCourseJpaEntity().getYear())
                        .season(entity.getCourseJpaEntity().getSeason())
                        .sbjSection(entity.getSectionJpaEntity().getSctNm())
                        .lSct(entity.getSectionJpaEntity().getLSct())
                        .mSct(entity.getSectionJpaEntity().getMSct())
                        .sSct(entity.getSectionJpaEntity().getSSct())
                        .college(entity.getDepartmentJpaEntity().getCollege())
                        .depart(entity.getDepartmentJpaEntity().getDepart())
                        .grade(entity.getGrade())
                        .crseNo(entity.getCourseJpaEntity().getCrseNo())
                        .sbjctNm(entity.getCourseJpaEntity().getSubjectJpaEntity().getSbjetNm())
                        .subjctCd(entity.getCourseJpaEntity().getSubjectJpaEntity().getSbjetCd())
                        .realLecTime(entity.getRealLecTime())
                        .lecTimes(lectureTimeListMapper(entity.getLectureTimes()))
                        .credit(entity.getCredit())
                        .lecCr(entity.getLecCr())
                        .professor(entity.getProfNm())
                        .building(entity.getBuilding())
                        .room(entity.getRoom())
                        .credit(entity.getCredit())
                        .lang(entity.getLang())
                        .isRemote(entity.isRemote())
                        .preSbjet(entity.getPreSbjet())
                        .postSbjet(entity.getPostSbjet())
                        .evaluation(evaluationMapper(entity.getCourseJpaEntity().getEvaluationJpaEntity()))
                        .isHumanities(entity.isHumanities())
                        .isSdg(entity.isSdg())
                        .isFlipped(entity.isFlipped())
                        .isNU(entity.isNU())
                        .isDgKp(entity.isDgKp())
                        .isSu(entity.isSu())
                        .note(entity.getNote())
                        .build()
        ).toList();
    }

    private List<LectureTime> lectureTimeListMapper(List<LectureTimeJpaEntity> entities) {
        return entities.stream().map(
                entity -> LectureTime.builder()
                        .day(entity.getDay())
                        .timeCode(entity.getTimeCode())
                        .build()
        ).toList();
    }

    private Evaluation evaluationMapper(EvaluationJpaEntity entity) {
        return Evaluation.builder()
                .attendance(entity.getAttendance())
                .midExam(entity.getMidExam())
                .finalExam(entity.getFinalExam())
                .assignment(entity.getAssignment())
                .presentation(entity.getPresentation())
                .debate(entity.getDebate())
                .safetyEdu(entity.getSafetyEdu())
                .etc(entity.getEtc())
                .total(entity.getTotal())
                .build();
    }
}

