package web.development.services.dto.input;

import web.development.services.dto.input.baseEntities.BaseEntityDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BrandDto extends BaseEntityDto {

    private String name;

    public BrandDto(String name) {
        this.name = name;
    }

    public BrandDto() {
    }
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
