package knu.team7.syllabus.infrastructure.adapter;

import knu.team7.syllabus.application.port.in.command.SubjectCodeCommand;
import knu.team7.syllabus.application.port.out.CreateSubjectCodePort;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.infrastructure.adapter.persistence.entity.SubjectCodeJpaEntity;
import knu.team7.syllabus.infrastructure.adapter.persistence.repository.SubjectCodeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class SubjectCodePersistenceAdapter implements CreateSubjectCodePort {
    private final SubjectCodeRepository subjectCodeRepository;
    @Override
    public void createSubjectCode(List<SubjectCodeCommand> list) {
        List<SubjectCodeJpaEntity> saveJpaEntities = list.stream().map(
                        command -> SubjectCodeJpaEntity.builder()
                                .sbjetCd(command.sbjetCd())
                                .sbjetNm(command.sbjetNm())
                                .build())
//                .filter(entity -> !subjectCodeRepository.existsById(entity.getSbjetCd()))
                .toList();
        for (SubjectCodeJpaEntity entity : saveJpaEntities) {
            if (!subjectCodeRepository.existsById(entity.getSbjetCd())) {
                subjectCodeRepository.save(entity);
            }
        }
    }
}
