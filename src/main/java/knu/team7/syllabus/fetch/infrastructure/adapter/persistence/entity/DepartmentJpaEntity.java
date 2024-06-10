package knu.team7.syllabus.fetch.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "FetchDepartmentJpaEntity")
@Table(name = "department", uniqueConstraints = {@UniqueConstraint(columnNames = {"college", "depart"})})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DepartmentJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String college;    // 개설대학
    private String depart;    // 개설학과

    @Builder
    public DepartmentJpaEntity(Long id, String college, String depart) {
        this.id = id;
        this.college = college;
        this.depart = depart;
    }
}
