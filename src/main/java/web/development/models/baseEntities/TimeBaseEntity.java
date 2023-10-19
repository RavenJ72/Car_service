package web.development.models.baseEntities;

import jakarta.persistence.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class TimeBaseEntity extends BaseEntity {
    @CreationTimestamp
    @Column(name = "created", updatable = false, columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "modified", columnDefinition = "TIMESTAMP(6)")
    private LocalDateTime modified;

}

