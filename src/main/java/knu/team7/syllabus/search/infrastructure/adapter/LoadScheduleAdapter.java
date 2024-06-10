package knu.team7.syllabus.search.infrastructure.adapter;

import com.querydsl.core.types.dsl.BooleanExpression;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.QScheduleJpaEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadScheduleAdapter{
    public void createSchedule() {
        QScheduleJpaEntity qYourEntity = QScheduleJpaEntity.scheduleJpaEntity;
        BooleanExpression predicate = qYourEntity.id.isNotNull();
    }
}
