package az.ingress.universitycourseplatform.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@SQLRestriction("deleted = false")
public class Department extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String building;
}
