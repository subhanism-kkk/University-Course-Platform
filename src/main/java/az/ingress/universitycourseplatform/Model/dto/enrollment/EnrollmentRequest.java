package az.ingress.universitycourseplatform.Model.dto.enrollment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentRequest {

    private Long courseId;
    private Long studentId;
    private LocalDateTime enrolledAt;
}
