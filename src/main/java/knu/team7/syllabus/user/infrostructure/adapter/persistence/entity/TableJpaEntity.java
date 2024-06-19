package knu.team7.syllabus.user.infrostructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "time_table")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TableJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private int year;
    private String season;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> crseNos;

    @Builder(toBuilder = true)
    public TableJpaEntity(Long id, String userId, int year, String season, List<String> crseNos) {
        this.id = id;
        this.userId = userId;
        this.year = year;
        this.season = season;
        this.crseNos = crseNos;
    }
}
