package web.development.dto.input.baseEntities;

import java.util.UUID;

public class BaseEntityDto {
    public UUID id;


    public BaseEntityDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
