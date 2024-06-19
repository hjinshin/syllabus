package knu.team7.syllabus.fetch.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.fetch.application.port.in.command.EvaluationCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateEvaluationPort;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.EvaluationJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.CourseRepository;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@PersistenceAdapter
@RequiredArgsConstructor
public class EvaluationPersistenceAdapter implements CreateEvaluationPort {
    private final EvaluationRepository evaluationRepository;
    private final CourseRepository courseRepository;

    @Override
    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            retryFor = DataIntegrityViolationException.class
    )
    @Transactional
    public void createEvaluation(Set<EvaluationCommand> list) {
        List<EvaluationJpaEntity> saveJpaEntities = new ArrayList<>();
        for (EvaluationCommand command : list) {
            Optional<CourseJpaEntity> courseOpt = courseRepository.findByCrseNoAndYearAndSeason(
                    command.crseNo(), command.year(), command.season()
            );
            if(courseOpt.isEmpty()) {
                continue;
            }
            CourseJpaEntity course = courseOpt.get();
            EvaluationJpaEntity entity = getExistORNew(
                    EvaluationJpaEntity.builder()
                            .attendance(command.attendance())
                            .midExam(command.midExam())
                            .finalExam(command.finalExam())
                            .assignment(command.assignment())
                            .presentation(command.presentation())
                            .debate(command.debate())
                            .safetyEdu(command.safetyEdu())
                            .etc(command.etc())
                            .total(command.total())
                            .courseJpaEntity(course)
                            .build());
            if(entity != null)
                saveJpaEntities.add(entity);
        }
        if(!saveJpaEntities.isEmpty())
            evaluationRepository.saveAll(saveJpaEntities);
    }

    private EvaluationJpaEntity getExistORNew(EvaluationJpaEntity entity) {
        Optional<EvaluationJpaEntity> existingEntityOptional = evaluationRepository.findByCourseJpaEntity(entity.getCourseJpaEntity());
        if (existingEntityOptional.isEmpty()) {
            return entity;
        }
        EvaluationJpaEntity existingEntity = existingEntityOptional.get();
        if (Objects.equals(entity, existingEntity)) {
            return null;
        }
        existingEntity.setAttendance(entity.getAttendance());
        existingEntity.setMidExam(entity.getMidExam());
        existingEntity.setFinalExam(entity.getFinalExam());
        existingEntity.setAssignment(entity.getAssignment());
        existingEntity.setPresentation(entity.getPresentation());
        existingEntity.setDebate(entity.getDebate());
        existingEntity.setSafetyEdu(entity.getSafetyEdu());
        existingEntity.setEtc(entity.getEtc());
        existingEntity.setTotal(entity.getTotal());

        return existingEntity;
    }

}
