package web.development.services.interfaces.internalApi;

import web.development.services.dto.input.RoleDto;

import java.util.List;

public interface RoleInternalService<ID> {
    void deleteById(ID id);  // Delete
}
