package web.development.models.entities;

import jakarta.persistence.*;
import web.development.models.baseEntities.TimeBaseEntity;

import java.util.*;

@Entity
@Table(name = "brands")
public class Brand extends TimeBaseEntity {

    private String name;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.REMOVE, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Model> models = new ArrayList<>();

    public Brand() {
    }

    public Brand(String name) {
        this.name = name;
    }
    @Column(name = "name", length = 255, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Model> getModels() {
        return models;
    }

    public void setModels(List<Model> models) {
        this.models = models;
    }
}
