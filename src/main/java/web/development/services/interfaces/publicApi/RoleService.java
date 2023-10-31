package web.development.services.interfaces.publicApi;

import web.development.models.entities.Role;
import web.development.services.dto.input.RoleDto;

import java.util.List;

public interface RoleService<ID> {

    RoleDto save(RoleDto userRole);  // Create/Update

    RoleDto findById(ID id);  // Read

    List<RoleDto> findAll();  // Read
}
