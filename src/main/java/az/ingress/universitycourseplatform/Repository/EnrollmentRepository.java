package az.ingress.universitycourseplatform.Repository;

import az.ingress.universitycourseplatform.Entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);

    List<Enrollment> findByStudentId(Long studentId);

    List<Enrollment> findByCourseId(Long courseId);

    List<Enrollment> findAllByStudentId(Long studentId);

    List<Enrollment> findAllByCourseId(Long courseId);

}
