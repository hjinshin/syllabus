package knu.team7.syllabus.fetch.infrastructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.fetch.application.port.out.LoadCoursePort;
import knu.team7.syllabus.fetch.domain.model.Course;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity.CourseJpaEntity;
import knu.team7.syllabus.fetch.infrastructure.adapter.persistence.repository.CourseRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadCourseAdapter implements LoadCoursePort {
    private final CourseRepository courseRepository;
    @Override
    public List<Course> loadCourseByYearAndSeason(int year, String season) {
        List<CourseJpaEntity> list = courseRepository.findAllByYearAndSeason(year, season);
        return list.stream().map(
                entity -> Course.builder()
                        .id(entity.getId())
                        .crseNo(entity.getCrseNo())
                        .year(entity.getYear())
                        .season(entity.getSeason())
                        .build()
        ).toList();
    }
}
