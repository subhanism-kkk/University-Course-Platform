package az.ingress.universitycourseplatform.Model.dto.enrollment;

import az.ingress.universitycourseplatform.Model.EnrollmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentResponse {
    private Long id;
    private Long studentId;
    private String studentName;
    private Long courseId;
    private String courseTitle;
    private EnrollmentStatus status;
    private LocalDateTime enrolledAt;
}

