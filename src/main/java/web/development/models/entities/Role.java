package web.development.models.entities;

import jakarta.persistence.*;
import web.development.models.baseEntities.BaseEntity;
import web.development.models.baseEntities.TimeBaseEntity;
import web.development.models.converters.RoleTypeConverter;
import web.development.models.enums.RoleType;

import java.util.*;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {



    private RoleType role;

    @OneToMany(mappedBy = "role", orphanRemoval = true,fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    public Role() {

    }
    public Role(RoleType role) {
        this.role = role;
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 11, nullable = false)
    public RoleType getRole() {
        return role;
    }

    private void setRole(RoleType role) {
        this.role = role;
    }
}
