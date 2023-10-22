package web.development.dto.input;

import web.development.dto.input.baseEntities.BaseEntityDto;
import web.development.models.enums.RoleType;

import java.util.UUID;

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
