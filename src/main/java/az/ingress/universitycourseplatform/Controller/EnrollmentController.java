package az.ingress.universitycourseplatform.Controller;

import az.ingress.universitycourseplatform.Model.CustomPage;
import az.ingress.universitycourseplatform.Model.EnrollmentStatus;
import az.ingress.universitycourseplatform.Model.dto.enrollment.EnrollmentRequest;
import az.ingress.universitycourseplatform.Model.dto.enrollment.EnrollmentResponse;
import az.ingress.universitycourseplatform.Service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping()
    @ResponseStatus(CREATED)
    public EnrollmentResponse enrollStudent(@RequestBody EnrollmentRequest request) {
        return enrollmentService.enrollStudent(request);
    }

    @PutMapping("/{enrollmentId}/status")
    @ResponseStatus(OK)
    public EnrollmentResponse changeEnrollmentStatus(
            @PathVariable Long enrollmentId,
            @RequestParam EnrollmentStatus status) {
        return enrollmentService.changeEnrollmentStatus(enrollmentId, status);
    }

    @GetMapping
    public CustomPage<EnrollmentResponse> getAllEnrollments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return enrollmentService.getAllEnrollments(pageable);
    }

    @GetMapping("/enrollments/{id}")
    @ResponseStatus(OK)
    public EnrollmentResponse getEnrollmentById(@PathVariable Long id) {
        return enrollmentService.getEnrollmentById(id);
    }

    @GetMapping("/students/{studentId}/enrollments")
    @ResponseStatus(OK)
    //Which courses has a specific student enrolled in
    // GET http://localhost:8080/api/v1/enrollments/students/4/enrollments //!!!TRY THIS!!!
    public List<EnrollmentResponse> getEnrollmentsByStudent(
            @PathVariable Long studentId) {
        return enrollmentService.getEnrollmentsByStudent(studentId);
    }

    @GetMapping("/courses/{courseId}/enrollments")
    @ResponseStatus(OK)
    public List<EnrollmentResponse> getEnrollmentsByCourse(
            @PathVariable Long courseId) {
        return enrollmentService.getEnrollmentsByCourse(courseId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteEnrollments(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id);
    }
}
