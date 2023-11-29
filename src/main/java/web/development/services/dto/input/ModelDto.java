package web.development.services.dto.input;

import lombok.Data;
import lombok.NoArgsConstructor;
import web.development.services.dto.input.baseEntities.BaseEntityDto;
import web.development.models.enums.ModelCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ModelDto extends BaseEntityDto {

    private String name;

    private ModelCategory category;

    private String imageUrl;

    private Integer startYear;

    private Integer endYear;

    private String brand;

    public ModelDto() {
    }

    public ModelDto(String name, ModelCategory category, String imageUrl, Integer startYear, Integer endYear, String brand) {
        this.name = name;
        this.category = category;
        this.imageUrl = imageUrl;
        this.startYear = startYear;
        this.endYear = endYear;
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(ModelCategory category) {
        this.category = category;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public void setEndYear(Integer endYear) {
        this.endYear = endYear;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    public String getName() {
        return name;
    }

    @NotNull(message = "Category cannot be null")
    public ModelCategory getCategory() {
        return category;
    }

    @NotBlank(message = "Image URL cannot be blank")
    @Size(min = 2, message = "Category must contains more than 2 characters")
    public String getImageUrl() {
        return imageUrl;
    }

    @NotNull(message = "Start year cannot be null")
    public Integer getStartYear() {
        return startYear;
    }
    @NotNull(message = "End year cannot be null")
    public Integer getEndYear() {
        return endYear;
    }
    @NotNull(message = "Brand cannot be null")
    @Size(min = 2, max = 15, message = "Brand must be between 2 and 15 characters")
    public String getBrand() {
        return brand;
    }


}