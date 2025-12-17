package az.ingress.universitycourseplatform.Service;

import az.ingress.universitycourseplatform.Entity.Enrollment;
import az.ingress.universitycourseplatform.Entity.Student;
import az.ingress.universitycourseplatform.Mapper.EnrollmentMapper;
import az.ingress.universitycourseplatform.Mapper.StudentMapper;
import az.ingress.universitycourseplatform.Model.NotFoundException;
import az.ingress.universitycourseplatform.Model.dto.enrollment.EnrollmentResponse;
import az.ingress.universitycourseplatform.Model.dto.student.StudentRequest;
import az.ingress.universitycourseplatform.Model.dto.student.StudentResponse;
import az.ingress.universitycourseplatform.Repository.EnrollmentRepository;
import az.ingress.universitycourseplatform.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final StudentMapper studentMapper;


    public StudentResponse createStudent(StudentRequest request) {
        var student = StudentMapper.toEntity(request);
        var savedStudent = studentRepository.save(student);

        return StudentMapper.toResponse(savedStudent);
    }

    public StudentResponse updateStudent(Long id, StudentRequest request) {
        var student = fetchStudentById(id);

        var updatedStudent = StudentMapper.updateEntityFromRequest(student, request);

        studentRepository.save(updatedStudent);

        return StudentMapper.toResponse(updatedStudent);
    }

    public StudentResponse getStudentsById(Long id) {
        return StudentMapper.toResponse(fetchStudentById(id));
    }

    public void deleteStudent(Long id) {
        var student = fetchStudentById(id);

        student.setDeleted(true);
        student.setDeletedAt(LocalDateTime.now());
        studentRepository.save(student);
    }

    public List<EnrollmentResponse> getStudentEnrollments(Long studentId) {

        // optional but recommended: validate student exists
        studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        List<Enrollment> enrollments =
                enrollmentRepository.findAllByStudentId(studentId);

        return enrollments.stream()
                .map(EnrollmentMapper::toResponse)
                .toList();
    }

    public void restoreStudent(Long id) {
        // Use the custom method that ignores the soft-delete filter
        Student student = studentRepository.findByIdWithDeleted(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));

        // Validation check (optional, but good practice)
        if (!student.isDeleted()) {
            throw new RuntimeException("Student with ID: " + id + " is already active.");
        }

        student.setDeleted(false);
        student.setDeletedAt(null);

        // IMPORTANT: Must save the changes back to the database
        studentRepository.save(student);
    }

    public Student fetchStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not Found"));
    }
}
