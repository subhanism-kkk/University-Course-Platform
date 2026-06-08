package az.ingress.universitycourseplatform.Service;

import az.ingress.universitycourseplatform.Entity.Course;
import az.ingress.universitycourseplatform.Entity.Department;
import az.ingress.universitycourseplatform.Entity.Enrollment;
import az.ingress.universitycourseplatform.Entity.Instructor;
import az.ingress.universitycourseplatform.Mapper.CourseMapper;
import az.ingress.universitycourseplatform.Mapper.EnrollmentMapper;
import az.ingress.universitycourseplatform.Model.CustomPage;
import az.ingress.universitycourseplatform.Model.NotFoundException;
import az.ingress.universitycourseplatform.Model.dto.course.CourseRequest;
import az.ingress.universitycourseplatform.Model.dto.course.CourseResponse;
import az.ingress.universitycourseplatform.Model.dto.enrollment.EnrollmentResponse;
import az.ingress.universitycourseplatform.Repository.CourseRepository;
import az.ingress.universitycourseplatform.Repository.DepartmentRepository;
import az.ingress.universitycourseplatform.Repository.EnrollmentRepository;
import az.ingress.universitycourseplatform.Repository.InstructorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final InstructorRepository instructorRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final CourseMapper courseMapper;

    @Transactional
    public CourseResponse createCourse(CourseRequest request) {
        var course = CourseMapper.toEntity(request);
        var savedCourse = courseRepository.save(course);

        return CourseMapper.toResponse(savedCourse);
    }

    @Transactional
    public CourseResponse updateCourse(Long id, CourseRequest request) {
        var course = fetchCourseById(id);

        CourseMapper.updateEntityFromRequest(course, request);

        if (request.getDepartmentId() != null) {
            Department department = departmentRepository.findById(request.getDepartmentId()).
                    orElseThrow(() -> new NotFoundException("Department not found"));
            course.setDepartment(department);
        }

        if (request.getInstructorId() != null) {
            Instructor instructor = instructorRepository.findById(request.getInstructorId()).
                    orElseThrow(() -> new NotFoundException("Instructor not found"));
            course.setInstructor(instructor);
        }
        courseRepository.save(course);

        return CourseMapper.toResponse(course);
    }

    public CustomPage<CourseResponse> getAllCourses(Pageable pageable) {

        Page<CourseResponse> page =
                courseRepository.findAll(pageable)
                        .map(CourseMapper::toResponse);

        return new CustomPage<>(
                page.getContent(),
                page.getNumber(),
                page.getSize()
        );
    }

    public CourseResponse getCourseById(Long id) {
        return CourseMapper.toResponse(fetchCourseById(id));
    }

    @Transactional
    public void deleteCourse(Long id) {
        var course = fetchCourseById(id);
        course.setDeleted(true);
        course.setDeletedAt(LocalDateTime.now());

        courseRepository.save(course);
    }

    public List<EnrollmentResponse> getCourseEnrollments(Long id) {
        // checking if it exists
        fetchCourseById(id);

        List<Enrollment> enrollments = enrollmentRepository.findAllByCourseId(id);

        return enrollments.stream()
                .map(EnrollmentMapper::toResponse)
                .toList();

    }

    public Course fetchCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not Found"));
    }
}
