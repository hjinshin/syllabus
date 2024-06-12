package knu.team7.syllabus.search.infrastructure.adapter;

import com.querydsl.core.types.dsl.BooleanExpression;
import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.search.application.port.in.command.ScheduleCommand;
import knu.team7.syllabus.search.application.port.out.LoadSchedulePort;
import knu.team7.syllabus.search.domain.model.Schedule;
import knu.team7.syllabus.search.infrastructure.adapter.persistence.entity.QScheduleJpaEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadScheduleAdapter implements LoadSchedulePort {
//    public void load() {
//        QScheduleJpaEntity qYourEntity = QScheduleJpaEntity.scheduleJpaEntity;
//        BooleanExpression predicate = qYourEntity.id.isNotNull();
//    }

    @Override
    public List<Schedule> loadSchedule(ScheduleCommand command) {
        return null;
    }
}
