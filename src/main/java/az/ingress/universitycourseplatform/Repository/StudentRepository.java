package az.ingress.universitycourseplatform.Repository;

import az.ingress.universitycourseplatform.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query(value = "SELECT s FROM Student s WHERE s.id = :id")
    Optional<Student> findByIdWithDeleted(Long id);
}
