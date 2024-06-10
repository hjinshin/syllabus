package knu.team7.syllabus.fetch.infrastructure.adapter;

import knu.team7.syllabus.fetch.application.port.in.command.DepartmentCommand;
import knu.team7.syllabus.fetch.application.port.in.command.EvaluationCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateEvaluationPort;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.fetch.domain.model.Evaluation;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.DepartmentJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.EvaluationRepository;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.EvaluationJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.SubjectCodeJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class EvaluationPersistenceAdapter implements CreateEvaluationPort {
    private final EvaluationRepository evaluationRepository;
    @Override
    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            retryFor = DataIntegrityViolationException.class
    )
    @Transactional
    public List<Evaluation> createEvaluation(List<EvaluationCommand> list) {
        saveEntityWithRetry(list);
        return list.stream().map(
                item -> Evaluation.builder()
                        .id(evaluationRepository.findByCourseJpaEntity(CourseJpaEntity.builder()
                                .id(item.course().getId())
                                .crseNo(item.course().getCrseNo())
                                .year(Integer.parseInt(item.course().getYear()))
                                .season(item.course().getSeason())
                                .build()).getId())
                        .attendance(item.attendance())
                        .midExam(item.midExam())
                        .finalExam(item.finalExam())
                        .assignment(item.assignment())
                        .presentation(item.presentation())
                        .debate(item.debate())
                        .safetyEdu(item.safetyEdu())
                        .etc(item.etc())
                        .total(item.total())
                        .course(item.course())
                        .build()
        ).toList();
    }

    public void saveEntityWithRetry(List<EvaluationCommand> list) {
        Set<EvaluationJpaEntity> saveJpaEntities = list.stream().map(
                        command -> EvaluationJpaEntity.builder()
                                .attendance(command.attendance())
                                .midExam(command.midExam())
                                .finalExam(command.finalExam())
                                .assignment(command.assignment())
                                .presentation(command.presentation())
                                .debate(command.debate())
                                .safetyEdu(command.safetyEdu())
                                .etc(command.etc())
                                .total(command.total())
                                .courseJpaEntity(CourseJpaEntity.builder()
                                        .id(command.course().getId())
                                        .season(command.course().getSeason())
                                        .year(Integer.parseInt(command.course().getYear()))
                                        .subjectCodeJpaEntity(SubjectCodeJpaEntity.builder()
                                                .sbjetCd(command.course().getSubjectCode().getSbjetCd())
                                                .sbjetNm(command.course().getSubjectCode().getSbjetNm())
                                                .build())
                                        .build())
                                .build()
                ).filter(entity -> !evaluationRepository.existsByCourseJpaEntity(entity.getCourseJpaEntity()))
                .collect(Collectors.toSet());
        evaluationRepository.saveAll(saveJpaEntities);
    }
}
