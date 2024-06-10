package knu.team7.syllabus.fetch.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.fetch.application.port.in.command.ListCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateSubjectSectionPort;
import knu.team7.syllabus.fetch.domain.model.SubjectSection;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.SubjectSectionJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.SubjectSectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.retry.annotation.Retryable;

import java.util.List;

@Retryable
@PersistenceAdapter
@RequiredArgsConstructor
public class SubjectSectionPersistenceAdapter implements CreateSubjectSectionPort {
    private final SubjectSectionRepository subjectSectionRepository;
    @Override
    public List<SubjectSection> createSubjectSection(List<ListCommand> list) {

        saveEntityWithRetry(list);
        return list.stream().map(
                item -> SubjectSection.builder()
                        .codeId(getCode(item))
                        .sbjetSctnm(item.section())
                        .lSct(item.lCodeNm())
                        .mSct(item.mCodeNm())
                        .sSct(item.sCodeNm())
                        .build()
        ).toList();
    }

    private String getCode(ListCommand item) {
        if (item.sCodeId() != null) {
            return item.sCodeId();
        }
        if (item.mCodeId() != null) {
            return item.mCodeId();
        }
        return item.lCodeId();
    }

    public void saveEntityWithRetry(List<ListCommand> list) {
        List<SubjectSectionJpaEntity> saveJpaEntities = list.stream().map(
                        command -> SubjectSectionJpaEntity.builder()
                                .codeId(getCode(command))
                                .sbjetSctnm(command.section())
                                .lSct(command.lCodeNm())
                                .mSct(command.mCodeNm())
                                .sSct(command.sCodeNm())
                                .build()
                )
                .filter(entity -> !subjectSectionRepository.existsById(entity.getCodeId()))
                .toList();

        subjectSectionRepository.saveAll(saveJpaEntities);
    }
}
