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


    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}

