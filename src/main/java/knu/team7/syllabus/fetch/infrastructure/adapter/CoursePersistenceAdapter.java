package knu.team7.syllabus.fetch.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.fetch.application.port.in.command.CourseCommand;
import knu.team7.syllabus.fetch.application.port.out.CreateCoursePort;
import knu.team7.syllabus.fetch.domain.model.Course;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.CourseRepository;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@PersistenceAdapter
@RequiredArgsConstructor
public class CoursePersistenceAdapter implements CreateCoursePort {
    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;

    @Retryable(
            maxAttempts = 3,
            backoff = @Backoff(delay = 1000),
            retryFor = DataIntegrityViolationException.class
    )
    @Transactional
    public List<Course> createCourse(Set<CourseCommand> set) {
        List<CourseJpaEntity> saveEntities = set.stream().map(
                        command -> CourseJpaEntity.builder()
                                .crseNo(command.crseNo())
                                .year(command.year())
                                .season(command.season())
                                .subjectJpaEntity(subjectRepository.findById(command.subjectCd()).orElse(null))
                                .build())
                .filter(entity -> !courseRepository.existsByCrseNoAndYearAndSeason(
                        entity.getCrseNo(), entity.getYear(), entity.getSeason()))
                .toList();

        List<CourseJpaEntity> entities = courseRepository.saveAll(saveEntities);

        return entities.stream().map(
                entity -> Course.builder()
                        .id(entity.getId())
                        .crseNo(entity.getCrseNo())
                        .year(entity.getYear())
                        .season(entity.getSeason())
                        .build()
        ).toList();
    }
}
