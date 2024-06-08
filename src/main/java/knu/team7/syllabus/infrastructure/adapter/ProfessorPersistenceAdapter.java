package knu.team7.syllabus.infrastructure.adapter;

import knu.team7.syllabus.application.port.in.command.ProfessorCommand;
import knu.team7.syllabus.application.port.out.CreateProfessorPort;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.domain.model.Professor;
import knu.team7.syllabus.infrastructure.adapter.persistence.entity.ProfessorJpaEntity;
import knu.team7.syllabus.infrastructure.adapter.persistence.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ProfessorPersistenceAdapter implements CreateProfessorPort {
    private final ProfessorRepository professorRepository;
    @Override
    public List<Professor> createProfessor( List<ProfessorCommand> list) {
        List<ProfessorJpaEntity> saveJpaEntities = list.stream().map(
                        command -> ProfessorJpaEntity.builder()
                                .profNm(command.profNm())
                                .profTel(command.profTel())
                                .profEmail(command.profEmail())
                                .build())
//                .filter(entity -> !professorRepository.existsByProfNmAndProfTelAndProfEmail(entity.getProfNm(), entity.getProfTel(), entity.getProfEmail()))
                .toList();
        for (ProfessorJpaEntity entity : saveJpaEntities) {
            if (!professorRepository.existsByProfNmAndProfTelAndProfEmail(entity.getProfNm(), entity.getProfTel(), entity.getProfEmail())) {
                professorRepository.save(entity);
            }
        }
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
}
