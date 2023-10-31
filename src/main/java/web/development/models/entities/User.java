package web.development.models.entities;

import jakarta.persistence.*;
import web.development.models.baseEntities.TimeBaseEntity;
import web.development.models.converters.RoleTypeConverter;

import java.util.*;

@Entity
@Table(name = "users")
public class User extends TimeBaseEntity {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private String imageUrl;

    @Convert(converter = RoleTypeConverter.class)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "seller",orphanRemoval = true, cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    private List<Offer> offers = new ArrayList<>();

    public User() {

    }
    @Column(name = "username", length = 255, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Column(name = "password", length = 255, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name = "firstName", length = 255, nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name = "lastName", length = 255, nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Column(name = "isActive")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
    @Column(name = "imageUrl", length = 512, nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
