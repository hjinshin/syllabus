package knu.team7.syllabus.infrastructure.adapter;

import knu.team7.syllabus.application.port.in.command.CourseCommand;
import knu.team7.syllabus.application.port.out.CreateCoursePort;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.domain.model.Course;
import knu.team7.syllabus.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.infrastructure.adapter.persistence.repository.CourseRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class CoursePersistenceAdapter implements CreateCoursePort {
    private final CourseRepository courseRepository;
    @Override
    public List<Course> createCourse(List<CourseCommand> list) {
        List<CourseJpaEntity> courseJpaEntities = list.stream().map(
                command -> CourseJpaEntity.builder()
                        .crseNo(command.crseNo())
                        .year(Integer.parseInt(command.year()))
                        .season(command.season())
                        .build())
            .filter(entity -> !courseRepository.existsByCrseNoAndYearAndSeason(entity.getCrseNo(), entity.getYear(), entity.getSeason()))
            .toList();

        courseRepository.saveAll(courseJpaEntities);

        return list.stream().map(
                item -> Course.builder()
                        .id(courseRepository.findByCrseNoAndYearAndSeason(item.crseNo(), Integer.parseInt(item.year()), item.season())
                                .getId())
                        .crseNo(item.crseNo())
                        .year(item.year())
                        .season(item.season())
                        .build()
        ).toList();

    }
}
