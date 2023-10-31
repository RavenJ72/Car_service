package web.development.services.dto.input;

import web.development.services.dto.input.baseEntities.BaseEntityDto;

public class BrandDto extends BaseEntityDto {
    public String name;

    public BrandDto( String name) {
        this.name = name;
    }

    public BrandDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
