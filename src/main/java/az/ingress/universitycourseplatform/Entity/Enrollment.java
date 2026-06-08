package az.ingress.universitycourseplatform.Entity;

import az.ingress.universitycourseplatform.Model.EnrollmentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Entity
@Table(name = "enrollment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLRestriction("deleted = false")
public class Enrollment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Student student;

    @ManyToOne
    Course course;

    @Enumerated(EnumType.STRING)
    @Column(name = "enrollment_status", nullable = false)
    EnrollmentStatus enrollmentStatus;

    LocalDateTime enrolledAt;
}
