package web.development.models.baseEntities;

import jakarta.persistence.*;



import java.util.UUID;
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

}
