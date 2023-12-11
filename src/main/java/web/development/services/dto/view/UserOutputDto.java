package web.development.services.dto.view;


import web.development.services.dto.input.baseEntities.BaseEntityDto;

public class UserOutputDto extends BaseEntityDto {

    public String username;
    public Boolean isActive;

    private String firstName;
    private String lastName;
    public String imageUrl;
    public String role_id;

    public UserOutputDto(String username, Boolean isActive, String firstName, String lastName, String imageUrl, String role_id) {
        this.username = username;
        this.isActive = isActive;
        this.firstName = firstName;
        this.lastName = lastName;
        this.imageUrl = imageUrl;
        this.role_id = role_id;
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

    public UserOutputDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "UserOutputDto{" +
                "username='" + username + '\'' +
                ", isActive=" + isActive +
                ", imageUrl='" + imageUrl + '\'' +
                ", role_id=" + role_id +
                '}';
    }
}
