package web.development.dto.input;

import web.development.dto.input.baseEntities.BaseEntityDto;

import java.util.UUID;

public class UserDto extends BaseEntityDto {
    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public Boolean isActive;
    public String imageUrl;
    public RoleDto role;

    public UserDto( String username, String password, String firstName, String lastName, Boolean isActive, String imageUrl, RoleDto role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.imageUrl = imageUrl;
        this.role = role;
    }

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

}
