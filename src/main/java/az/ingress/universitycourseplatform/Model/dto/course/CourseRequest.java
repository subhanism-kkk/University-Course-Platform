package az.ingress.universitycourseplatform.Model.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequest {
    private String title;
    private Integer credits;
    private Long departmentId;
    private Long instructorId;
}
