package knu.team7.syllabus.fetch.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.fetch.application.port.in.command.DepartmentCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateDepartmentPort;
import knu.team7.syllabus.fetch.domain.model.Department;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.DepartmentJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@PersistenceAdapter
@RequiredArgsConstructor
public class DepartmentPersistenceAdapter implements CreateDepartmentPort {
    private final DepartmentRepository departmentRepository;

    @Override
    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            retryFor = DataIntegrityViolationException.class
    )
    @Transactional
    public List<Department> createDepartment(Set<DepartmentCommand> list) {
        List<DepartmentJpaEntity> saveJpaEntities = list.stream().map(
                        command -> DepartmentJpaEntity.builder()
                                .college(command.college())
                                .depart(command.depart())
                                .build())
                .map(this::getExistingOrNew)
                .toList();

        departmentRepository.saveAll(saveJpaEntities);
        List<DepartmentJpaEntity> entities = departmentRepository.findAll();
        return entities.stream().map(
                entity -> Department.builder()
                        .id(entity.getId())
                        .college(entity.getCollege())
                        .depart(entity.getDepart())
                        .build()).toList();
    }

    private DepartmentJpaEntity getExistingOrNew(DepartmentJpaEntity entity) {
        Optional<DepartmentJpaEntity> existingEntityOptional = departmentRepository.findByCollegeAndDepart(entity.getCollege(), entity.getDepart());
        if (existingEntityOptional.isEmpty()) {
            return entity;
        }
        return existingEntityOptional.get();
    }
}
