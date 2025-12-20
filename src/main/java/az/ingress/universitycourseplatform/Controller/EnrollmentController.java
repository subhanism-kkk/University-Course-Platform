package az.ingress.universitycourseplatform.Controller;

import az.ingress.universitycourseplatform.Model.EnrollmentStatus;
import az.ingress.universitycourseplatform.Model.dto.enrollment.EnrollmentRequest;
import az.ingress.universitycourseplatform.Model.dto.enrollment.EnrollmentResponse;
import az.ingress.universitycourseplatform.Model.dto.student.StudentResponse;
import az.ingress.universitycourseplatform.Service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping()
    @ResponseStatus(CREATED)
    public EnrollmentResponse enrollStudent(@RequestBody EnrollmentRequest request){
        return enrollmentService.enrollStudent(request);
    }

    @PutMapping("/{enrollmentId}/status")
    @ResponseStatus(OK)
    public EnrollmentResponse changeEnrollmentStatus(
            @PathVariable Long enrollmentId,
            @RequestParam EnrollmentStatus status){
        return enrollmentService.changeEnrollmentStatus(enrollmentId, status);
    }

    @GetMapping("/enrollments/{id}")
    @ResponseStatus(OK)
    public EnrollmentResponse getEnrollmentById(@PathVariable Long id){
        return enrollmentService.getEnrollmentById(id);
    }

    @GetMapping("/students/{studentId}/enrollments")
    @ResponseStatus(OK)
    public List<EnrollmentResponse> getEnrollmentsByStudent(
            @PathVariable Long studentId) {
        return enrollmentService.getEnrollmentsByStudent(studentId);
    }

    @GetMapping("/courses/{coursesId}/enrollments")
    @ResponseStatus(OK)
    public List<EnrollmentResponse> getEnrollmentsByCourse(
            @PathVariable Long coursesId) {
        return enrollmentService.getEnrollmentsByCourse(coursesId);
    }

    @DeleteMapping("/{id}")
    public void deleteEnrollments(@PathVariable Long id){
        enrollmentService.deleteEnrollment(id);
    }
}
