package knu.team7.syllabus.fetch.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.fetch.application.port.in.command.CourseCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateCoursePort;
import knu.team7.syllabus.fetch.domain.model.Course;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.SubjectCodeJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.CourseRepository;
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
public class CoursePersistenceAdapter implements CreateCoursePort {
    private final CourseRepository courseRepository;


    public List<Course> createCourse(List<CourseCommand> list) {
        saveEntityWithRetry(list);

        return list.stream().map(
                item -> Course.builder()
                        .id(courseRepository
                                .findByCrseNoAndYearAndSeason(
                                        item.crseNo(), Integer.parseInt(item.year()), item.season())
                                .getId())
                        .crseNo(item.crseNo())
                        .year(item.year())
                        .season(item.season())
                        .subjectCode(item.subjectCode())
                        .build()
        ).toList();
    }

    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            retryFor = DataIntegrityViolationException.class
    )
    @Transactional
    public void saveEntityWithRetry(List<CourseCommand> list) {
        Set<CourseJpaEntity> courseJpaEntities = list.stream().map(
                        command -> CourseJpaEntity.builder()
                                .crseNo(command.crseNo())
                                .year(Integer.parseInt(command.year()))
                                .season(command.season())
                                .subjectCodeJpaEntity(SubjectCodeJpaEntity.builder()
                                        .sbjetCd(command.subjectCode().getSbjetCd())
                                        .sbjetNm(command.subjectCode().getSbjetNm())
                                        .build())
                                .build())
                .filter(entity -> !courseRepository
                        .existsByCrseNoAndYearAndSeason(
                                entity.getCrseNo(), entity.getYear(), entity.getSeason()))
                .collect(Collectors.toSet());
        courseRepository.saveAll(courseJpaEntities);
    }
}
