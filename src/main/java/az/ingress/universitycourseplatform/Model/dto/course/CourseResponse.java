package az.ingress.universitycourseplatform.Model.dto.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponse {
    private Long id;
    private String title;
    private Integer credits;
    private Long departmentId;
    private String departmentName;
    private Long instructorId;
    private String instructorName;
}
