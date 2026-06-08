package az.ingress.universitycourseplatform.Controller;

import az.ingress.universitycourseplatform.Model.CustomPage;
import az.ingress.universitycourseplatform.Model.dto.course.CourseResponse;
import az.ingress.universitycourseplatform.Model.dto.department.DepartmentRequest;
import az.ingress.universitycourseplatform.Model.dto.department.DepartmentResponse;
import az.ingress.universitycourseplatform.Service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    @ResponseStatus(CREATED)
    public DepartmentResponse createDepartment(@RequestBody DepartmentRequest request) {
        return departmentService.createDepartment(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(OK)
    public DepartmentResponse updateDepartment(@PathVariable Long id, @RequestBody DepartmentRequest request) {
        return departmentService.updateDepartment(id, request);
    }

    @GetMapping
    public CustomPage<DepartmentResponse> getAllDepartments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return departmentService.getAllDepartments(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public DepartmentResponse getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping("/departments/{id}/courses")
    @ResponseStatus(OK)
    public List<CourseResponse> getCoursesByDepartment(@PathVariable Long id) {
        return departmentService.getCoursesByDepartment(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}
