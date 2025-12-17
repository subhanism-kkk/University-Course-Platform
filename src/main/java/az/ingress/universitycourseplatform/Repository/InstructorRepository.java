package az.ingress.universitycourseplatform.Repository;

import az.ingress.universitycourseplatform.Entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    boolean existsByEmail(String email);
}

