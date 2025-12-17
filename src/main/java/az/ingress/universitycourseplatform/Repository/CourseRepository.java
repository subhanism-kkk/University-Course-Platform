package az.ingress.universitycourseplatform.Repository;

import az.ingress.universitycourseplatform.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByTitle(String title);

    List<Course> findAllByDepartmentId(Long departmentId);

    List<Course> findCoursesByInstructorId(Long instructorId);

}

