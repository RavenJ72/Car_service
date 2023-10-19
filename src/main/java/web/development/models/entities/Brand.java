package web.development.models.entities;

import jakarta.persistence.*;
import web.development.models.baseEntities.TimeBaseEntity;

import java.util.*;

@Entity
@Table(name = "brands")
public class Brand extends TimeBaseEntity {
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Model> models = new ArrayList<>();

    public Brand() {
    }

    public Brand(String name, List<Model> models) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}