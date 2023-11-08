package web.development.services.interfaces.internalApi;

import web.development.models.entities.Role;
import web.development.models.enums.RoleType;
import web.development.services.dto.input.RoleDto;

import java.util.List;

public interface RoleInternalService<ID> {
    void deleteById(ID id);  // Delete

    Role findByRoleType(RoleType roleType);


}
