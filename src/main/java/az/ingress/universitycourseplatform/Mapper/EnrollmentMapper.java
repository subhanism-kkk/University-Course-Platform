package az.ingress.universitycourseplatform.Mapper;

import az.ingress.universitycourseplatform.Entity.Enrollment;
import az.ingress.universitycourseplatform.Model.dto.enrollment.EnrollmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnrollmentMapper {

    public static EnrollmentResponse toResponse(Enrollment enrollment) {
        EnrollmentResponse response = new EnrollmentResponse();
        response.setId(enrollment.getId());
        response.setStatus(enrollment.getEnrollmentStatus());
        response.setEnrolledAt(enrollment.getEnrolledAt());

        if (enrollment.getStudent() != null) {
            response.setStudentId(enrollment.getStudent().getId());
            response.setStudentName(enrollment.getStudent().getFullName());
        }

        if (enrollment.getCourse() != null) {
            response.setCourseId(enrollment.getCourse().getId());
            response.setCourseTitle(enrollment.getCourse().getTitle());
        }

        return response;
    }
}

