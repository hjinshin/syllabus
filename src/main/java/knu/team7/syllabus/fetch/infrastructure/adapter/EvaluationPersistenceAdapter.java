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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public void createEvaluation(List<EvaluationCommand> list) {
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
            saveJpaEntities.add(entity);
        }

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
        return existingEntity.toBuilder()
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
