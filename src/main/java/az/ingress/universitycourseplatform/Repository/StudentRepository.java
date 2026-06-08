package az.ingress.universitycourseplatform.Repository;

import az.ingress.universitycourseplatform.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Adding nativeQuery = true and querying the actual database table name 'students'
    @Query(value = "SELECT * FROM students WHERE id = :id", nativeQuery = true)
    Optional<Student> findByIdWithDeleted(@Param("id") Long id);
}