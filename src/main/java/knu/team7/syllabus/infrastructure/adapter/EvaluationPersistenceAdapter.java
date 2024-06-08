package knu.team7.syllabus.infrastructure.adapter;

import knu.team7.syllabus.application.port.in.command.EvaluationCommand;
import knu.team7.syllabus.application.port.out.CreateEvaluationPort;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.domain.model.Evaluation;
import knu.team7.syllabus.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.infrastructure.adapter.persistence.entity.EvaluationJpaEntity;
import knu.team7.syllabus.infrastructure.adapter.persistence.repository.EvaluationRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class EvaluationPersistenceAdapter implements CreateEvaluationPort {
    private final EvaluationRepository evaluationRepository;
    @Override
    public List<Evaluation> createEvaluation(List<EvaluationCommand> list) {
        List<EvaluationJpaEntity> saveJpaEntities = list.stream().map(
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
                                        .build())
                                .build()
                ).filter(entity -> !evaluationRepository.existsByCourseJpaEntity(entity.getCourseJpaEntity()))
                .toList();

        evaluationRepository.saveAll(saveJpaEntities);
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
}
