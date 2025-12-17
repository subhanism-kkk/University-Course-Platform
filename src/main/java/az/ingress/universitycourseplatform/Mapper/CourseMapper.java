package az.ingress.universitycourseplatform.Mapper;

import az.ingress.universitycourseplatform.Entity.Course;
import az.ingress.universitycourseplatform.Model.dto.course.CourseRequest;
import az.ingress.universitycourseplatform.Model.dto.course.CourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseMapper {

    public static Course toEntity(CourseRequest request) {
        Course course = new Course();
        course.setTitle(request.getTitle());
        course.setCredits(request.getCredits());
        return course;
    }

    public static CourseResponse toResponse(Course course) {
        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setTitle(course.getTitle());
        response.setCredits(course.getCredits());

        if (course.getDepartment() != null) {
            response.setDepartmentId(course.getDepartment().getId());
            response.setDepartmentName(course.getDepartment().getName());
        }

        if (course.getInstructor() != null) {
            response.setInstructorId(course.getInstructor().getId());
            response.setInstructorName(course.getInstructor().getFullName());
        }

        return response;
    }


    public static Course updateEntityFromRequest(Course entity, CourseRequest request) {

        if (request.getTitle() != null) {
            entity.setTitle(request.getTitle());
        }

        if (request.getCredits() != null) {
            entity.setCredits(request.getCredits());
        }


        return entity;
    }
}

