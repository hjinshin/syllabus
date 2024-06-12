package knu.team7.syllabus.search.infrastructure.adapter.persistence.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity(name = "SearchDepartmentJpaEntity")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentJpaEntity that = (DepartmentJpaEntity) o;
        return Objects.equals(college, that.college) &&
                Objects.equals(depart, that.depart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(college, depart);
    }
}
