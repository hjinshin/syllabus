package knu.team7.syllabus.fetch.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.fetch.application.port.in.command.SyllabusCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateSyllabusPort;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.SyllabusJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.CourseRepository;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.SyllabusRepository;
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
public class SyllabusPersistenceAdapter implements CreateSyllabusPort {
    private final SyllabusRepository syllabusRepository;
    private final CourseRepository courseRepository;

    @Override
    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            retryFor = DataIntegrityViolationException.class
    )
    @Transactional
    public void createSyllabus(List<List<SyllabusCommand>> list) {

        List<SyllabusJpaEntity> saveJpaEntities = new ArrayList<>();
        for (List<SyllabusCommand> commandList : list) {
            if(commandList == null)     continue;
            if(commandList.size() == 0) continue;
            Optional<CourseJpaEntity> courseOpt = courseRepository.findByCrseNoAndYearAndSeason(
                    commandList.get(0).crseNo(), Integer.parseInt(commandList.get(0).year()), commandList.get(0).season()
            );
            if(courseOpt.isEmpty())      continue;
            List<SyllabusJpaEntity> entities = commandList.stream().map(
                    command -> SyllabusJpaEntity.builder()
                            .crseGoal(command.crseGoal())
                            .eduGoal(command.eduGoal())
                            .summary(command.summary())
                            .textbook(command.textbook())
                            .evalMethd(command.evalMethd())
                            .intviTimeLoc(command.intviTimeLoc())
                            .refer(command.refer())
                            .doPlan(command.doPlan())
                            .profNm(command.profNm())
                            .profTel(command.profTel())
                            .profEmail(command.profEmail())
                            .courseJpaEntity(courseOpt.get()) // Or handle null case appropriately
                            .build())
                    .map(this::getExistOrNew)
                    .filter(Objects::nonNull)
                    .toList();
            saveJpaEntities.addAll(entities);
        }

        syllabusRepository.saveAll(saveJpaEntities);
    }

    private SyllabusJpaEntity getExistOrNew(SyllabusJpaEntity entity) {
        Optional<SyllabusJpaEntity> existingEntityOptional = syllabusRepository.findByCourseJpaEntityAndDoPlan(
                entity.getCourseJpaEntity(), entity.getDoPlan()
        );
        if (existingEntityOptional.isEmpty()) {
            return entity;
        }
        SyllabusJpaEntity existingEntity = existingEntityOptional.get();
        if (Objects.equals(entity, existingEntity)) {
            return null;
        }
        return existingEntity.toBuilder()
                .crseGoal(entity.getCrseGoal())
                .eduGoal(entity.getEduGoal())
                .summary(entity.getSummary())
                .textbook(entity.getTextbook())
                .evalMethd(entity.getEvalMethd())
                .intviTimeLoc(entity.getIntviTimeLoc())
                .profNm(entity.getProfNm())
                .profTel(entity.getProfTel())
                .profEmail(entity.getProfEmail())
                .build();
    }
}
