package knu.team7.syllabus.fetch.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.fetch.application.port.in.command.SectionCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateSectionPort;
import knu.team7.syllabus.fetch.domain.model.Section;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.SectionJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.SectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Retryable
@PersistenceAdapter
@RequiredArgsConstructor
public class SectionPersistenceAdapter implements CreateSectionPort {
    private final SectionRepository sectionRepository;
    @Override
    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            retryFor = DataIntegrityViolationException.class
    )
    @Transactional
    public List<Section> createSection(Set<SectionCommand> set) {
        List<SectionJpaEntity> saveJpaEntities = set.stream().map(
                        command -> SectionJpaEntity.builder()
                                .codeId(command.codeId())
                                .sctNm(command.sctNm())
                                .lSct(command.lSct())
                                .mSct(command.mSct())
                                .sSct(command.sSct())
                                .build())
                .map(this::getExistingOrNew)
                .filter(Objects::nonNull)
                .toList();

        List<SectionJpaEntity> entities = sectionRepository.saveAll(saveJpaEntities);
        return entities.stream().map(
                entity -> Section.builder()
                        .codeId(entity.getCodeId())
                        .sctNm(entity.getSctNm())
                        .lSct(entity.getLSct())
                        .mSct(entity.getMSct())
                        .sSct(entity.getSSct())
                        .build()).toList();
    }

    private SectionJpaEntity getExistingOrNew(SectionJpaEntity entity) {
        Optional<SectionJpaEntity> existingEntityOptional = sectionRepository.findById(entity.getCodeId());
        if (existingEntityOptional.isEmpty()) {
            return entity;
        }
        SectionJpaEntity existingEntity = existingEntityOptional.get();

        if (Objects.equals(entity, existingEntity)) {
            return null;
        }
        return existingEntity.toBuilder()
                .sctNm(entity.getSctNm())
                .lSct(entity.getLSct())
                .mSct(entity.getMSct())
                .sSct(entity.getSSct())
                .build();
    }
}
