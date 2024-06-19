package knu.team7.syllabus.user.infrostructure.adapter;

import knu.team7.syllabus.core.annotation.PersistenceAdapter;
import knu.team7.syllabus.user.application.port.UpdateTablePort;
import knu.team7.syllabus.user.infrostructure.adapter.persistence.entity.TableJpaEntity;
import knu.team7.syllabus.user.infrostructure.adapter.persistence.repository.TimeTableRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class TablePersistenceAdapter implements UpdateTablePort {
    private final TimeTableRepository timeTableRepository;
    @Override
    public void updateUserTable(List<String> crseNos, int year, String season, String userId) {
        Optional<TableJpaEntity> existingEntity = timeTableRepository.findByYearAndSeasonAndUserId(year, season, userId);
        if (existingEntity.isEmpty()) {
            timeTableRepository.save(TableJpaEntity.builder()
                    .userId(userId)
                    .year(year)
                    .season(season)
                    .crseNos(crseNos)
                    .build());
            return;
        }
        TableJpaEntity entity = existingEntity.get();
        timeTableRepository.save(entity.toBuilder().crseNos(crseNos).build());
    }
}
