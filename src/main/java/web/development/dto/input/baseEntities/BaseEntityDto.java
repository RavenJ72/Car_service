package web.development.dto.input.baseEntities;

public class BaseEntityDto {
    public Long id;

    public BaseEntityDto(Long id) {
        this.id = id;
    }

    public BaseEntityDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
