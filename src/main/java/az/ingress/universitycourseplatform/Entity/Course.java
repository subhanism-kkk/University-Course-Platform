package az.ingress.universitycourseplatform.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(exclude = {"department", "instructor"}) // Prevents infinite logging loops
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLRestriction("deleted = false")
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    Integer credits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    Instructor instructor;
}