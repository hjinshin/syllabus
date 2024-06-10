package knu.team7.syllabus.fetch.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.fetch.application.port.in.command.ProfessorCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateProfessorPort;
import knu.team7.syllabus.fetch.domain.model.Professor;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.ProfessorJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.ProfessorRepository;
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
public class ProfessorPersistenceAdapter implements CreateProfessorPort {
    private final ProfessorRepository professorRepository;
    @Override
    public List<Professor> createProfessor( List<ProfessorCommand> list) {

        saveEntityWithRetry(list);
        return list.stream().map(
                item -> Professor.builder()
                        .id(professorRepository.findByProfNmAndProfTelAndProfEmail(item.profNm(), item.profTel(), item.profEmail())
                                .getId())
                        .profNm(item.profNm())
                        .profTel(item.profTel())
                        .profEmail(item.profEmail())
                        .build()
        ).toList();
    }

    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            retryFor = DataIntegrityViolationException.class
    )
    @Transactional
    public void saveEntityWithRetry(List<ProfessorCommand> list) {
        Set<ProfessorJpaEntity> saveJpaEntities = list.stream().map(
                        command -> ProfessorJpaEntity.builder()
                                .profNm(command.profNm())
                                .profTel(command.profTel())
                                .profEmail(command.profEmail())
                                .build())
                .filter(entity -> !professorRepository.existsByProfNmAndProfTelAndProfEmail(entity.getProfNm(), entity.getProfTel(), entity.getProfEmail()))
                .collect(Collectors.toSet());

        professorRepository.saveAll(saveJpaEntities);
    }
}
