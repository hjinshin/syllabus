package knu.team7.syllabus.fetch.infrastructure.adapter;

import knu.team7.syllabus.fetch.application.port.in.command.SubjectCodeCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateSubjectCodePort;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.SubjectCodeRepository;
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
public class SubjectCodePersistenceAdapter implements CreateSubjectCodePort {
    private final SubjectCodeRepository subjectCodeRepository;
    @Override
    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            retryFor = DataIntegrityViolationException.class
    )
    @Transactional
    public void createSubjectCode(List<SubjectCodeCommand> list) {
        Set<SubjectCodeJpaEntity> saveJpaEntities = list.stream().map(
                        command -> SubjectCodeJpaEntity.builder()
                                .sbjetCd(command.sbjetCd())
                                .sbjetNm(command.sbjetNm())
                                .build())
                .filter(entity -> !subjectCodeRepository.existsById(entity.getSbjetCd()))
                .collect(Collectors.toSet());
        subjectCodeRepository.saveAll(saveJpaEntities);
        }
}
