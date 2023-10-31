package web.development.models.baseEntities;

import jakarta.persistence.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;

@MappedSuperclass
public abstract class TimeBaseEntity extends BaseEntity {

    private LocalDateTime created;

    private LocalDateTime modified;

    @CreationTimestamp
    @Column(name = "created", updatable = false, columnDefinition = "TIMESTAMP(6)")
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    @UpdateTimestamp
    @Column(name = "modified", columnDefinition = "TIMESTAMP(6)")
    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}

