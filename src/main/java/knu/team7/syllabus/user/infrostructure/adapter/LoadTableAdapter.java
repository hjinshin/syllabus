package knu.team7.syllabus.user.infrostructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.user.application.port.LoadTablePort;
import knu.team7.syllabus.user.infrostructure.adapter.persistence.entity.TableJpaEntity;
import knu.team7.syllabus.user.infrostructure.adapter.persistence.repository.TimeTableRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class LoadTableAdapter implements LoadTablePort {
    private final TimeTableRepository timeTableRepository;
    @Override
    public List<String> loadTables(int year, String season, String userId) {
        Optional<TableJpaEntity> existingEntity = timeTableRepository.findByYearAndSeasonAndUserId(year, season, userId);
        if (existingEntity.isEmpty()) {
            return null;
        }
        TableJpaEntity entity = existingEntity.get();
        return entity.getCrseNos();
    }
}
