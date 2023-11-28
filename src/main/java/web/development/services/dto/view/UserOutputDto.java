package web.development.services.dto.view;


import web.development.services.dto.input.baseEntities.BaseEntityDto;

public class UserOutputDto extends BaseEntityDto {

    public String username;
    public Boolean isActive;

    public String imageUrl;
    public String role_id;

    public UserOutputDto(String username, Boolean isActive, String imageUrl, String role_id) {
        this.username = username;
        this.isActive = isActive;
        this.imageUrl = imageUrl;
        this.role_id = role_id;
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
