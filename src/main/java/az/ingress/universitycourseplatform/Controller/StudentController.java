package az.ingress.universitycourseplatform.Controller;

import az.ingress.universitycourseplatform.Model.dto.enrollment.EnrollmentResponse;
import az.ingress.universitycourseplatform.Model.dto.student.StudentRequest;
import az.ingress.universitycourseplatform.Model.dto.student.StudentResponse;
import az.ingress.universitycourseplatform.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(CREATED)
    public StudentResponse createStudent(@RequestBody StudentRequest request) {
        return studentService.createStudent(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public StudentResponse updateStudent(@PathVariable Long id,
                                         @RequestBody StudentRequest request) {
        return studentService.updateStudent(id, request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentsById(id);
    }

    @GetMapping("/students/{studentId}/enrollments")
    @ResponseStatus(OK)
    public List<EnrollmentResponse> getStudentEnrollments(
            @PathVariable Long studentId) {
        return studentService.getStudentEnrollments(studentId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteInstructor(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}

