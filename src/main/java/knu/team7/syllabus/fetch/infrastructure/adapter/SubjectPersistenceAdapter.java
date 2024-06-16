package knu.team7.syllabus.fetch.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.fetch.application.port.in.command.SubjectCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateSubjectPort;
import knu.team7.syllabus.fetch.domain.model.Subject;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.SubjectJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@PersistenceAdapter
@RequiredArgsConstructor
public class SubjectPersistenceAdapter implements CreateSubjectPort {
    private final SubjectRepository subjectRepository;
    @Override
    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            retryFor = DataIntegrityViolationException.class
    )
    @Transactional
    public List<Subject> createSubject(Set<SubjectCommand> set) {

        List<SubjectJpaEntity> saveJpaEntities = set.stream().map(
                        command -> SubjectJpaEntity.builder()
                                .sbjetCd(command.sbjetCd())
                                .sbjetNm(command.sbjetNm())
                                .build())
                .map(this::getExistingOrNew)
                .filter(Objects::nonNull)
                .toList();

        List<SubjectJpaEntity> entities = subjectRepository.saveAll(saveJpaEntities);
        return entities.stream().map(
                entity -> Subject.builder()
                        .sbjetCd(entity.getSbjetCd())
                        .sbjetNm(entity.getSbjetNm())
                        .build()).toList();
    }


    private SubjectJpaEntity getExistingOrNew(SubjectJpaEntity entity) {
        Optional<SubjectJpaEntity> existingEntityOptional = subjectRepository.findById(entity.getSbjetCd());
        if (existingEntityOptional.isEmpty()) {
            return entity;
        }
        SubjectJpaEntity existingEntity = existingEntityOptional.get();

        if (Objects.equals(entity, existingEntity)) {
            return null;
        }
        return existingEntity.toBuilder()
                .sbjetNm(entity.getSbjetNm())
                .build();
    }
}
