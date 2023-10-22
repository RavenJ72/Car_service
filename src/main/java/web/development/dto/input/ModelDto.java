package web.development.dto.input;

import web.development.dto.input.baseEntities.BaseEntityDto;
import web.development.models.enums.ModelCategory;

import java.time.LocalDateTime;
import java.util.UUID;

public class ModelDto extends BaseEntityDto {

    public String name;
    public ModelCategory category;
    public String imageUrl;
    public Integer startYear;
    public Integer endYear;
    public BrandDto brand;


    public ModelDto( String name, ModelCategory category, String imageUrl, Integer startYear, Integer endYear, BrandDto brand) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.endYear = endYear;
        this.brand = brand;
    }

    public ModelDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ModelCategory getCategory() {
        return category;
    }

    public void setCategory(ModelCategory category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public BrandDto getBrand() {
        return brand;
    }

    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }
}
