package az.ingress.universitycourseplatform.Service;

import az.ingress.universitycourseplatform.Entity.Course;
import az.ingress.universitycourseplatform.Entity.Department;
import az.ingress.universitycourseplatform.Mapper.CourseMapper;
import az.ingress.universitycourseplatform.Mapper.DepartmentMapper;
import az.ingress.universitycourseplatform.Model.NotFoundException;
import az.ingress.universitycourseplatform.Model.dto.course.CourseResponse;
import az.ingress.universitycourseplatform.Model.dto.department.DepartmentRequest;
import az.ingress.universitycourseplatform.Model.dto.department.DepartmentResponse;
import az.ingress.universitycourseplatform.Repository.CourseRepository;
import az.ingress.universitycourseplatform.Repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public DepartmentResponse createDepartment(DepartmentRequest request) {
        var department = DepartmentMapper.toEntity(request);
        var savedDepartment = departmentRepository.save(department);

        return DepartmentMapper.toResponse(savedDepartment);
    }

    @Transactional
    public DepartmentResponse updateDepartment(Long id, DepartmentRequest request) {
        var department = fetchDepartment(id);
        var updatedDepartment = DepartmentMapper.updateEntityFromRequest(department, request);
        departmentRepository.save(updatedDepartment);
        return DepartmentMapper.toResponse(updatedDepartment);
    }

    public DepartmentResponse getDepartmentById(Long id){
        return DepartmentMapper.toResponse(fetchDepartment(id));
    }

    @Transactional
    public void deleteDepartment(Long id) {
        var department = fetchDepartment(id);

        department.setDeleted(true);
        department.setDeletedAt(LocalDateTime.now());

        departmentRepository.save(department);
    }

    public List<CourseResponse> getCoursesByDepartment(Long departmentId) {
        // checking if it exists
        fetchDepartment(departmentId);

        List<Course> courses = courseRepository.findAllByDepartmentId(departmentId);

        return courses.stream()
                .map(CourseMapper::toResponse)
                .toList();
    }


        public Department fetchDepartment(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not Found"));
    }
}
