package web.development.services.dto.input;

import web.development.services.dto.input.baseEntities.BaseEntityDto;
import web.development.models.enums.RoleType;

import jakarta.validation.constraints.NotNull;

public class RoleDto extends BaseEntityDto {

    private RoleType role;

    public RoleDto(RoleType role) {
        this.role = role;
    }

    public RoleDto() {
    }
    @NotNull(message = "Role cannot be null")
    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
