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
import java.util.Set;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class DepartmentPersistenceAdapter implements CreateDepartmentPort {
    private final DepartmentRepository departmentRepository;
    @Override
    public List<Department> createDepartment(List<DepartmentCommand> list) {

        saveEntityWithRetry(list);
        return list.stream().map(
                item -> Department.builder()
                        .id(departmentRepository.findByCollegeAndDepart(item.college(), item.depart())
                                .getId())
                        .college(item.college())
                        .depart(item.depart())
                        .build()
        ).toList();
    }

    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            retryFor = DataIntegrityViolationException.class
    )
    @Transactional
    public void saveEntityWithRetry(List<DepartmentCommand> list) {
        Set<DepartmentJpaEntity> saveJpaEntities = list.stream().map(
                        command -> DepartmentJpaEntity.builder()
                                .college(command.college())
                                .depart(command.depart())
                                .build())
                .filter(entity -> !departmentRepository.existsByCollegeAndDepart(entity.getCollege(), entity.getDepart()))
                .collect(Collectors.toSet());
        departmentRepository.saveAll(saveJpaEntities);
    }
}
