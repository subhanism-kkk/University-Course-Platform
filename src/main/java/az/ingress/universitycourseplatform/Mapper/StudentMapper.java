package az.ingress.universitycourseplatform.Mapper;

import az.ingress.universitycourseplatform.Entity.Student;
import az.ingress.universitycourseplatform.Model.dto.student.StudentRequest;
import az.ingress.universitycourseplatform.Model.dto.student.StudentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    public static Student toEntity(StudentRequest request) {
        Student student = new Student();
        student.setFullName(request.getFullName());
        student.setEmail(request.getEmail());
        return student;
    }

    public static StudentResponse toResponse(Student student) {
        StudentResponse response = new StudentResponse();
        response.setId(student.getId());
        response.setFullName(student.getFullName());
        response.setEmail(student.getEmail());
        return response;
    }

    public static Student updateEntityFromRequest(Student entity, StudentRequest request) {

        if (request.getFullName() != null) {
            entity.setFullName(request.getFullName());
        }

        if (request.getEmail() != null) {
            entity.setEmail(request.getEmail());
        }

        // Add logic for all other updatable fields here

        return entity;
    }
}

