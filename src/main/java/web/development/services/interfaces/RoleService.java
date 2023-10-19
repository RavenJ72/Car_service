package web.development.services.interfaces;

import web.development.dto.input.RoleDto;
import web.development.models.entities.Role;

import java.util.List;

public interface RoleService<ID> {

    RoleDto save(RoleDto userRole);  // Create/Update

    RoleDto findById(ID id);  // Read

    List<RoleDto> findAll();  // Read

    void deleteById(ID id);  // Delete
}
