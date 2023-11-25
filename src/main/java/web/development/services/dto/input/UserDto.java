package web.development.services.dto.input;

import org.hibernate.validator.constraints.UniqueElements;
import web.development.services.dto.input.baseEntities.BaseEntityDto;
import web.development.models.enums.RoleType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import web.development.util.validation.UniqueUserName;

public class UserDto extends BaseEntityDto {

    @UniqueUserName
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private String imageUrl;
    private String role;

    public UserDto(String username, String password, String firstName, String lastName, Boolean isActive, String imageUrl, String role) {
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
    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 4,message = "Username must be at least 4 characters long")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Size(min = 4,message = "Password must be at least 4 characters long")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @NotEmpty(message = "First name cannot be empty")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @NotEmpty(message = "Last name cannot be empty")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @NotNull(message = "isActive cannot be null")
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @NotNull(message = "Role cannot be null")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
