package az.ingress.universitycourseplatform.Controller;

import az.ingress.universitycourseplatform.Model.CustomPage;
import az.ingress.universitycourseplatform.Model.dto.course.CourseResponse;
import az.ingress.universitycourseplatform.Model.dto.instructor.InstructorRequest;
import az.ingress.universitycourseplatform.Model.dto.instructor.InstructorResponse;
import az.ingress.universitycourseplatform.Service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/instructors")
public class InstructorController {

    public final InstructorService instructorService;

    @PostMapping
    @ResponseStatus(CREATED)
    public InstructorResponse createInstructor(
            @RequestBody InstructorRequest request) {
        return instructorService.createInstructor(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public InstructorResponse updateInstructor(
            @PathVariable Long id,
            @RequestBody InstructorRequest request) {
        return instructorService.updateInstructor(id, request);
    }

    @GetMapping
    public CustomPage<InstructorResponse> getAllInstructors(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return instructorService.getAllInstructors(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public InstructorResponse getInstructorById(@PathVariable Long id) {
        return instructorService.getInstructorById(id);
    }

    @GetMapping("/instructor/{instructorId}/courses")
    @ResponseStatus(OK)
    public List<CourseResponse> getCoursesTaughtByInstructor(
            @PathVariable Long instructorId) {
        return instructorService.getCoursesTaughtByInstructor(instructorId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
    }
}
