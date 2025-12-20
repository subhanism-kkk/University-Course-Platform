package az.ingress.universitycourseplatform.Controller;

import az.ingress.universitycourseplatform.Model.dto.course.CourseRequest;
import az.ingress.universitycourseplatform.Model.dto.course.CourseResponse;
import az.ingress.universitycourseplatform.Model.dto.enrollment.EnrollmentResponse;
import az.ingress.universitycourseplatform.Service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {
    private final CourseService courseService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CourseResponse createCourse(@RequestBody CourseRequest request) {
        return courseService.createCourse(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public CourseResponse updateCourse(@PathVariable Long id, @RequestBody CourseRequest request) {
        return courseService.updateCourse(id, request);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public CourseResponse getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/enrollments/{id}")
    @ResponseStatus(OK)
    public List<EnrollmentResponse> getCourseEnrollments(@PathVariable Long id) {
        return courseService.getCourseEnrollments(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
    }
}
