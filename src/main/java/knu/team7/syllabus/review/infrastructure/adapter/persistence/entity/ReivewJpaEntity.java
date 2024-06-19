package knu.team7.syllabus.review.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReivewJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private int year;
    private String season;
    private String crseNo;
    private String profNm;
    private float rating;
    private String contents;

    @DateTimeFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
    private LocalDateTime createdAt;

    @Builder
    public ReivewJpaEntity(Long id, String userId, int year, String season, String crseNo, String profNm, float rating, String contents, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.year = year;
        this.season = season;
        this.crseNo = crseNo;
        this.profNm = profNm;
        this.rating = rating;
        this.contents = contents;
        this.createdAt = createdAt;
    }
}
