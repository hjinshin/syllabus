package knu.team7.syllabus.search.infrastructure.adapter.persistence.repository.custom;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import knu.team7.syllabus.search.application.port.in.command.LectureCommand;
import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class LectureQueryDslRepositoryCustomImpl implements LectureQueryDslRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<LectureJpaEntity> findAllLectures(LectureCommand command) {
        QLectureJpaEntity lectureJpaEntity = QLectureJpaEntity.lectureJpaEntity;
        QCourseJpaEntity courseJpaEntity = QCourseJpaEntity.courseJpaEntity;
        QDepartmentJpaEntity departmentJpaEntity = QDepartmentJpaEntity.departmentJpaEntity;
        QSectionJpaEntity sectionJpaEntity = QSectionJpaEntity.sectionJpaEntity;
        QLectureTimeJpaEntity lectureTimeJpaEntity = QLectureTimeJpaEntity.lectureTimeJpaEntity;
        QEvaluationJpaEntity evaluationJpaEntity = QEvaluationJpaEntity.evaluationJpaEntity;

        BooleanBuilder builder = new BooleanBuilder();
        if (command.year() > 0) {
            builder.and(courseJpaEntity.year.eq(command.year()));
        }
        if(StringUtils.hasText(command.season()))
            builder.and(courseJpaEntity.year.eq(command.year()));

        if(StringUtils.hasText(command.crseNo()))
           builder.and(courseJpaEntity.crseNo.eq(command.crseNo()));

        if(StringUtils.hasText(command.sbjctNm()))
            builder.and(courseJpaEntity.subjectJpaEntity.sbjetNm.eq(command.sbjctNm()));

        if(StringUtils.hasText(command.subjctCd()))
            builder.and(courseJpaEntity.subjectJpaEntity.sbjetCd.eq(command.subjctCd()));

        if(StringUtils.hasText(command.professor()))
         builder.and(lectureJpaEntity.profNm.eq(command.professor()));

        if(StringUtils.hasText(command.college()))
          builder.and(departmentJpaEntity.college.eq(command.college()));

        if(StringUtils.hasText(command.depart()))
           builder.and(departmentJpaEntity.depart.eq(command.depart()));

        if(StringUtils.hasText(command.lang()))
           builder.and(lectureJpaEntity.lang.eq(command.lang()));

        if(StringUtils.hasText(command.sbjctSection()))
            builder.and(sectionJpaEntity.sctNm.eq(command.sbjctSection()));

        if(StringUtils.hasText(command.lSect()))
         builder.and(sectionJpaEntity.lSct.eq(command.lSect()));

        if(StringUtils.hasText(command.mSect()))
            builder.and(sectionJpaEntity.mSct.eq(command.mSect()));

        if(StringUtils.hasText(command.sSect()))
            builder.and(sectionJpaEntity.sSct.eq(command.sSect()));

        if(command.evaluation() != null) {
            if(command.evaluation().getAttendance() != -1)
                builder.and(evaluationJpaEntity.attendance.eq(command.evaluation().getAttendance()));
            if(command.evaluation().getMidExam() != -1)
                builder.and(evaluationJpaEntity.midExam.eq(command.evaluation().getMidExam()));
            if(command.evaluation().getFinalExam() != -1)
                builder.and(evaluationJpaEntity.finalExam.eq(command.evaluation().getFinalExam()));
            if(command.evaluation().getAssignment() != -1)
                builder.and(evaluationJpaEntity.assignment.eq(command.evaluation().getAssignment()));
            if(command.evaluation().getPresentation() != -1)
                builder.and(evaluationJpaEntity.presentation.eq(command.evaluation().getPresentation()));
            if(command.evaluation().getDebate() != -1)
                builder.and(evaluationJpaEntity.debate.eq(command.evaluation().getDebate()));
            if(command.evaluation().getSafetyEdu() != -1)
                builder.and(evaluationJpaEntity.safetyEdu.eq(command.evaluation().getSafetyEdu()));
            if(command.evaluation().getEtc() != -1)
                builder.and(evaluationJpaEntity.etc.eq(command.evaluation().getEtc()));
            if(command.evaluation().getTotal() != -1)
                builder.and(evaluationJpaEntity.total.eq(command.evaluation().getTotal()));
        }

        if(command.isRemote())
            builder.and(lectureJpaEntity.isRemote.eq(true));

        if(command.isHumanities())
            builder.and(lectureJpaEntity.isHumanities.eq(true));

        if(command.isSdg())
            builder.and(lectureJpaEntity.isSdg.eq(true));

        if(command.isFlipped())
            builder.and(lectureJpaEntity.isFlipped.eq(true));

        if(command.isNU())
            builder.and(lectureJpaEntity.isNU.eq(true));

        if(command.isDgKp())
            builder.and(lectureJpaEntity.isDgKp.eq(true));

        if(command.isSu())
            builder.and(lectureJpaEntity.isSu.eq(true));



        return queryFactory
                .selectFrom(lectureJpaEntity)
                .leftJoin(lectureJpaEntity.courseJpaEntity, courseJpaEntity).fetchJoin()
                .leftJoin(courseJpaEntity.evaluationJpaEntity, evaluationJpaEntity).fetchJoin()
                .leftJoin(lectureJpaEntity.departmentJpaEntity, departmentJpaEntity).fetchJoin()
                .leftJoin(lectureJpaEntity.sectionJpaEntity, sectionJpaEntity).fetchJoin()
                .leftJoin(lectureJpaEntity.lectureTimes, lectureTimeJpaEntity).fetchJoin()
                .where(builder)
                .fetch();
    }
}
