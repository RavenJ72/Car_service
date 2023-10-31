package web.development.models.entities;

import jakarta.persistence.*;
import web.development.models.baseEntities.TimeBaseEntity;
import web.development.models.converters.ModelCategoryConverter;
import web.development.models.enums.ModelCategory;


@Entity
@Table(name = "models")
public class Model extends TimeBaseEntity {


    private String name;


    private ModelCategory category;


    private String imageUrl;


    private Integer startYear;


    private Integer endYear;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    public Model() {
    }

    @Column(name = "name", length = 255, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Convert(converter = ModelCategoryConverter.class)
    @Column(name = "category", length = 11, nullable = false)
    public ModelCategory getCategory() {
        return category;
    }

    private void setCategory(ModelCategory category) {
        this.category = category;
    }
    @Column(name = "imageUrl", length = 512, nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @Column(name = "startYear", length = 11, nullable = false)
    public Integer getStartYear() {
        return startYear;
    }

    private void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }
    @Column(name = "endYear", length = 11, nullable = false)
    private Integer getEndYear() {
        return endYear;
    }

    private void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public Brand getBrand() {
        return brand;
    }

    private void setBrand(Brand brand) {
        this.brand = brand;
    }
}
