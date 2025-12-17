package az.ingress.universitycourseplatform.Mapper;

import az.ingress.universitycourseplatform.Entity.Department;
import az.ingress.universitycourseplatform.Model.dto.department.DepartmentRequest;
import az.ingress.universitycourseplatform.Model.dto.department.DepartmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DepartmentMapper {

    public static Department toEntity(DepartmentRequest request) {
        Department department = new Department();
        department.setName(request.getName());
        department.setBuilding(request.getBuilding());
        return department;
    }

    public static DepartmentResponse toResponse(Department department) {
        DepartmentResponse response = new DepartmentResponse();
        response.setId(department.getId());
        response.setName(department.getName());
        response.setBuilding(department.getBuilding());
        return response;
    }

    public static Department updateEntityFromRequest(Department entity, DepartmentRequest request) {

        if (request.getBuilding() != null) {
            entity.setBuilding(request.getBuilding());
        }

        if (request.getName() != null) {
            entity.setName(request.getName());
        }


        return entity;
    }
}


