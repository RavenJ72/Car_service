package web.development.dto.input;

import web.development.dto.input.baseEntities.BaseEntityDto;

import java.time.LocalDateTime;
import java.util.UUID;

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
