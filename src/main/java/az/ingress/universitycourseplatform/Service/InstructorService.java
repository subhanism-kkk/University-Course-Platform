package az.ingress.universitycourseplatform.Service;

import az.ingress.universitycourseplatform.Entity.Course;
import az.ingress.universitycourseplatform.Entity.Department;
import az.ingress.universitycourseplatform.Entity.Instructor;
import az.ingress.universitycourseplatform.Mapper.CourseMapper;
import az.ingress.universitycourseplatform.Mapper.InstructorMapper;
import az.ingress.universitycourseplatform.Model.CustomPage;
import az.ingress.universitycourseplatform.Model.NotFoundException;
import az.ingress.universitycourseplatform.Model.dto.course.CourseResponse;
import az.ingress.universitycourseplatform.Model.dto.instructor.InstructorRequest;
import az.ingress.universitycourseplatform.Model.dto.instructor.InstructorResponse;
import az.ingress.universitycourseplatform.Repository.CourseRepository;
import az.ingress.universitycourseplatform.Repository.DepartmentRepository;
import az.ingress.universitycourseplatform.Repository.InstructorRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;

    @Transactional
    public InstructorResponse createInstructor(InstructorRequest request) {
        var instructor = InstructorMapper.toEntity(request);

        if (request.getDepartmentId() != null){
            var department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new NotFoundException("Department not found"));

            instructor.setDepartment(department);
        }

       instructorRepository.save(instructor);

        return InstructorMapper.toResponse(instructor);
    }

    @Transactional
    public InstructorResponse updateInstructor(Long id, InstructorRequest request) {
        var instructor = fetchInstructorById(id);

        Department newDepartment = null;

        if (request.getDepartmentId() != null){
            newDepartment = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new NotFoundException("Department not found"));
        }

       InstructorMapper.updateEntityFromRequest(instructor, request, newDepartment);

        instructorRepository.save(instructor);

        return InstructorMapper.toResponse(instructor);
    }

    public CustomPage<InstructorResponse> getAllInstructors(Pageable pageable) {

        Page<InstructorResponse> page =
                instructorRepository.findAll(pageable)
                        .map(InstructorMapper::toResponse);

        return new CustomPage<>(
                page.getContent(),
                page.getNumber(),
                page.getSize()
        );
    }

        public InstructorResponse getInstructorById(Long id) {
        return InstructorMapper.toResponse(fetchInstructorById(id));
    }

    public List<CourseResponse> getCoursesTaughtByInstructor(Long instructorId) {
        // checking if it exists
        fetchInstructorById(instructorId);

        List<Course> courses = courseRepository.findCoursesByInstructorId(instructorId);

        return courses.stream()
                .map(CourseMapper::toResponse)
                .toList();
    }

    @Transactional
    public void deleteInstructor(Long id) {
        var instructor = fetchInstructorById(id);
        instructor.setDeleted(true);
        instructor.setDeletedAt(LocalDateTime.now());

        instructorRepository.save(instructor);
    }

    public Instructor fetchInstructorById(Long id) {
        return instructorRepository.findById(id).orElseThrow(() -> new NotFoundException("Instructor not found"));
    }
}
