package knu.team7.syllabus.fetch.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.fetch.application.port.in.command.LectureCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateLecturePort;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.*;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.CourseRepository;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.DepartmentRepository;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.LectureRepository;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class LecturePersistenceAdapter implements CreateLecturePort {
    private final LectureRepository lectureRepository;
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final SectionRepository sectionRepository;

    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            retryFor = DataIntegrityViolationException.class
    )
    @Transactional
    public void createLecture(List<LectureCommand> list) {

        List<LectureJpaEntity> saveEntities = new ArrayList<>();
        for (LectureCommand command : list) {
            CourseJpaEntity courseEntity = findCourseEntity(command);
            if (courseEntity == null)  continue;
            DepartmentJpaEntity departmentEntity = findDepartEntity(command);
            if(departmentEntity == null) continue;
            SectionJpaEntity sectionEntity = findSectionEntity(command);
            if(sectionEntity == null) continue;

            LectureJpaEntity entity = LectureJpaEntity.builder()
                    .credit(Integer.parseInt(command.crdit()))
                    .lecCr(Integer.parseInt(command.thryTime()))
                    .pracCr(Integer.parseInt(command.prctsTime()))
                    .grade(command.estblGrade())
                    .building(command.lctrmInfo())
                    .room(command.rmnmCd())
                    .capacity(Integer.parseInt(command.attlcPrscpCnt()))
                    .lang(command.lang())
                    .isRemote(Objects.equals(command.expniSllbsYn(), "Y"))
                    .note(command.rmrk())
                    .preSbjet(command.preSbjet())
                    .postSbjet(command.postSbjet())
                    .realLecTime(command.lssnsRealTimeInfo())
                    .profNm(command.totalPrfssNm())
                    .isHumanities(command.humanities())
                    .isSdg(command.sdg())
                    .isFlipped(command.flipped())
                    .isNU(command.nU())
                    .isDgKp(command.dgKp())
                    .isSu(command.su())
                    .courseJpaEntity(courseEntity)
                    .departmentJpaEntity(departmentEntity)
                    .sectionJpaEntity(sectionEntity)
                    .lectureTimes(command.lssnsTimeInfo().stream().map(
                            item -> LectureTimeJpaEntity.builder()
                                    .day(item.getDay())
                                    .timeCode(item.getTimeCode())
                                    .build()).toList())
                    .build();
            entity = getExistingOrNew(entity);
            saveEntities.add(entity);
        }
        lectureRepository.saveAll(saveEntities);
    }

    private CourseJpaEntity findCourseEntity(LectureCommand command) {
        return courseRepository.findByCrseNoAndYearAndSeason(
                command.crseNo(), Integer.parseInt(command.estblYear()), command.estblSmstrSctnm()
        ).orElse(null);
    }

    private DepartmentJpaEntity findDepartEntity(LectureCommand command) {
        return departmentRepository.findByCollegeAndDepart(command.estblUnivNm(), command.estblDprtnNm())
                .orElse(null);
    }

    private SectionJpaEntity findSectionEntity(LectureCommand command) {
        return sectionRepository.findById(command.sectCd()).orElse(null);
    }

    private LectureJpaEntity getExistingOrNew(LectureJpaEntity entity) {
        Optional<LectureJpaEntity> existingEntityOptional = lectureRepository.findByCourseJpaEntity(entity.getCourseJpaEntity());
        if (existingEntityOptional.isEmpty()) {
            return entity;
        }
        LectureJpaEntity existingEntity = existingEntityOptional.get();
        if (Objects.equals(entity, existingEntity)) {
            return null;
        }
        return existingEntity.toBuilder()
                .credit(entity.getCredit())
                .lecCr(entity.getLecCr())
                .pracCr(entity.getPracCr())
                .grade(entity.getGrade())
                .building(entity.getBuilding())
                .room(entity.getRoom())
                .capacity(entity.getCapacity())
                .lang(entity.getLang())
                .isRemote(entity.isRemote())
                .note(entity.getNote())
                .preSbjet(entity.getPreSbjet())
                .postSbjet(entity.getPostSbjet())
                .realLecTime(entity.getRealLecTime())
                .profNm(entity.getProfNm())
                .isHumanities(entity.isHumanities())
                .isSdg(entity.isSdg())
                .isFlipped(entity.isFlipped())
                .isNU(entity.isNU())
                .isDgKp(entity.isDgKp())
                .isSu(entity.isSu())
                .departmentJpaEntity(entity.getDepartmentJpaEntity())
                .sectionJpaEntity(entity.getSectionJpaEntity())
                .lectureTimes(entity.getLectureTimes())
                .build();
    }
}
