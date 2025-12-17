package az.ingress.universitycourseplatform.Model.dto.instructor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorResponse {
    private Long id;
    private String fullName;
    private String email;
    private Long departmentId;
    private String departmentName;
}
