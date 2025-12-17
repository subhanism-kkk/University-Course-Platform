package az.ingress.universitycourseplatform.Service;

import az.ingress.universitycourseplatform.Entity.Course;
import az.ingress.universitycourseplatform.Entity.Department;
import az.ingress.universitycourseplatform.Entity.Instructor;
import az.ingress.universitycourseplatform.Mapper.CourseMapper;
import az.ingress.universitycourseplatform.Mapper.InstructorMapper;
import az.ingress.universitycourseplatform.Model.NotFoundException;
import az.ingress.universitycourseplatform.Model.dto.course.CourseResponse;
import az.ingress.universitycourseplatform.Model.dto.instructor.InstructorRequest;
import az.ingress.universitycourseplatform.Model.dto.instructor.InstructorResponse;
import az.ingress.universitycourseplatform.Repository.CourseRepository;
import az.ingress.universitycourseplatform.Repository.DepartmentRepository;
import az.ingress.universitycourseplatform.Repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;


    public InstructorResponse createInstructor(InstructorRequest request) {
        var instructor = InstructorMapper.toEntity(request);
        var savedInstructor = instructorRepository.save(instructor);

        return InstructorMapper.toResponse(savedInstructor);
    }

    public InstructorResponse updateInstructor(Long id, InstructorRequest request) {
        var instructor = fetchInstructorById(id);
        var updatedInstructor = InstructorMapper.updateEntityFromRequest(instructor, request);

        if (request.getDepartmentId() != null) {
            Department department = departmentRepository.findById(request.getDepartmentId())
                    .orElseThrow(() -> new NotFoundException("Department not found"));
        }

        instructorRepository.save(updatedInstructor);

        return InstructorMapper.toResponse(updatedInstructor);
    }

    public InstructorResponse getInstructorById(Long id) {
        return InstructorMapper.toResponse(fetchInstructorById(id));
    }

    public List<CourseResponse> getCoursesTaughtByInstructor(Long instructorId){
        // checking if it exists
        fetchInstructorById(instructorId);

        List<Course> courses = courseRepository.findCoursesByInstructorId(instructorId);

        return courses.stream()
                .map(CourseMapper::toResponse)
                .toList();
    }

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
