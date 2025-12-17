package az.ingress.universitycourseplatform.Service;

import az.ingress.universitycourseplatform.Entity.Course;
import az.ingress.universitycourseplatform.Entity.Enrollment;
import az.ingress.universitycourseplatform.Entity.Student;
import az.ingress.universitycourseplatform.Mapper.EnrollmentMapper;
import az.ingress.universitycourseplatform.Model.EnrollmentStatus;
import az.ingress.universitycourseplatform.Model.NotFoundException;
import az.ingress.universitycourseplatform.Model.dto.enrollment.EnrollmentResponse;
import az.ingress.universitycourseplatform.Repository.CourseRepository;
import az.ingress.universitycourseplatform.Repository.EnrollmentRepository;
import az.ingress.universitycourseplatform.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import az.ingress.universitycourseplatform.Model.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public EnrollmentResponse enrollStudent(Long courseId, Long studentId) {
        var course = fetchCourseById(courseId);
        var student = fetchStudentById(studentId);

        // prevent duplicate enrollment
        if (enrollmentRepository.existsByStudentIdAndCourseId(studentId, courseId)) {
            throw new BadRequestException("Student already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        enrollment.setEnrollmentStatus(EnrollmentStatus.ACTIVE);
        enrollment.setEnrolledAt(LocalDateTime.now());

        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        return EnrollmentMapper.toResponse(savedEnrollment);
    }
    @Transactional
    public EnrollmentResponse changeEnrollmentStatus(Long enrollmentId, EnrollmentStatus status){
        var enrollment = fetchEnrollmentById(enrollmentId);

        if (enrollment.getEnrollmentStatus() == EnrollmentStatus.DROPPED) {
            throw new BadRequestException("Dropped enrollment cannot be updated");
        }

        enrollment.setEnrollmentStatus(status);
        var savedEnrollment = enrollmentRepository.save(enrollment);

        return EnrollmentMapper.toResponse(savedEnrollment);
    }

    public EnrollmentResponse getEnrollmentById(Long enrollmentId){
        return EnrollmentMapper.toResponse(fetchEnrollmentById(enrollmentId));
    }

    public List<EnrollmentResponse> getEnrollmentsByStudent(Long studentId){

        //checking if the student exists
        fetchStudentById(studentId);

        List<Enrollment> enrollments = enrollmentRepository.findAllByStudentId(studentId);

        return enrollments.stream()
                .map(EnrollmentMapper::toResponse)
                .toList();
    }

    public List<EnrollmentResponse> getEnrollmentsByCourse(Long courseId){

        //checking if the course exists
        fetchCourseById(courseId);

        List<Enrollment> enrollments = enrollmentRepository.findAllByCourseId(courseId);

        return enrollments.stream()
                .map(EnrollmentMapper::toResponse)
                .toList();
    }
    @Transactional
    public void deleteEnrollment(Long enrollmentId){
        var enrollment = fetchEnrollmentById(enrollmentId);

        enrollment.setDeleted(true);
        enrollment.setDeletedAt(LocalDateTime.now());

        enrollmentRepository.save(enrollment);
    }

    public Student fetchStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found"));
    }

    public Course fetchCourseById(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found"));
    }

    public Enrollment fetchEnrollmentById(Long id){
        return enrollmentRepository.findById(id).orElseThrow(()-> new NotFoundException("Enrollment not found"));
    }
}
