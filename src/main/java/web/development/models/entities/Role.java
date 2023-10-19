package web.development.models.entities;

import jakarta.persistence.*;
import web.development.models.baseEntities.TimeBaseEntity;
import web.development.models.converters.RoleTypeConverter;
import web.development.models.enums.RoleType;

import java.util.*;

@Entity
@Table(name = "roles")
public class Role extends TimeBaseEntity {


    @Convert(converter = RoleTypeConverter.class)
    @Column(name = "role", length = 11, nullable = false)
    private RoleType role;

    @OneToMany(mappedBy = "role", orphanRemoval = true)
    private List<User> users = new ArrayList<>();


    public Role() {

    }
    public Role(RoleType role) {
        this.role = role;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}