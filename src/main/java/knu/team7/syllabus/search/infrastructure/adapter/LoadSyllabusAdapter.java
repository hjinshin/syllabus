package knu.team7.syllabus.search.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.search.application.port.in.command.SyllabusCommand;
import knu.team7.syllabus.search.application.port.out.LoadSyllabusPort;
import knu.team7.syllabus.search.domain.model.Syllabus;
import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.SyllabusJpaEntity;
import knu.team7.syllabus.search.infrastructure.adapter.persistence.repository.SyllabusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadSyllabusAdapter implements LoadSyllabusPort {
    private final SyllabusRepository syllabusRepository;
    @Override
    @Transactional(readOnly = true)
    public Syllabus loadSyllabus(SyllabusCommand command) {
        Optional<SyllabusJpaEntity> existingEntity = syllabusRepository.findSyllabusJpaEntity(
                command.doPlan(), command.crseNo(), command.season(), command.year()
        );
        if (existingEntity.isEmpty()) {
            throw new IllegalArgumentException("Syllabus does not exist.");
        }
        SyllabusJpaEntity entity = existingEntity.get();
        return Syllabus.builder()
                .crseNo(entity.getCourseJpaEntity().getCrseNo())
                .crseGoal(entity.getCrseGoal())
                .eduGoal(entity.getEduGoal())
                .summary(entity.getSummary())
                .textbook(entity.getTextbook())
                .evalMethd(entity.getEvalMethd())
                .intviTimeLoc(entity.getIntviTimeLoc())
                .refer(entity.getRefer())
                .doPlan(entity.getDoPlan())
                .profNm(entity.getProfNm())
                .profTel(entity.getProfTel())
                .profEmail(entity.getProfEmail())
                .build();
    }
}
