package az.ingress.universitycourseplatform.Mapper;

import az.ingress.universitycourseplatform.Entity.Department;
import az.ingress.universitycourseplatform.Entity.Instructor;
import az.ingress.universitycourseplatform.Model.dto.instructor.InstructorRequest;
import az.ingress.universitycourseplatform.Model.dto.instructor.InstructorResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstructorMapper {

    public static Instructor toEntity(InstructorRequest request) {
        Instructor instructor = new Instructor();
        instructor.setFullName(request.getFullName());
        instructor.setEmail(request.getEmail());
        return instructor;
    }

    public static InstructorResponse toResponse(@NonNull Instructor instructor) {
        InstructorResponse response = new InstructorResponse();
        response.setId(instructor.getId());
        response.setFullName(instructor.getFullName());
        response.setEmail(instructor.getEmail());

        if (instructor.getDepartment() != null) {
            response.setDepartmentId(instructor.getDepartment().getId());
            response.setDepartmentName(instructor.getDepartment().getName());
        }

        return response;
    }

    public static Instructor updateEntityFromRequest(Instructor entity, InstructorRequest request, Department department) {

        if (request.getFullName() != null) {
            entity.setFullName(request.getFullName());
        }

        if (request.getEmail() != null) {
            entity.setEmail(request.getEmail());
        }

        if (request.getDepartmentId() != null){
            entity.setDepartment(department);
        }

        return entity;
    }
}
