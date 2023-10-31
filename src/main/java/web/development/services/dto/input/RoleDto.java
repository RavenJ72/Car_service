package web.development.services.dto.input;

import web.development.services.dto.input.baseEntities.BaseEntityDto;
import web.development.models.enums.RoleType;

public class RoleDto extends BaseEntityDto {
    public RoleType role;

    public RoleDto( RoleType role) {
        this.role = role;
    }

    public RoleDto() {
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
