package web.development.models.baseEntities;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


import java.util.UUID;
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid-string")
    @GenericGenerator(name = "uuid-string",
            strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    public BaseEntity() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
