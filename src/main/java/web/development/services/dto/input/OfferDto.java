package web.development.services.dto.input;

import web.development.services.dto.input.baseEntities.BaseEntityDto;
import web.development.models.enums.EngineType;
import web.development.models.enums.TransmissionType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class OfferDto extends BaseEntityDto {
    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Engine type cannot be null")
    private EngineType engine;

    @NotBlank(message = "Image URL cannot be blank")
    private String imageUrl;

    @Positive(message = "Mileage must be a positive number")
    private Integer mileage;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be a positive number")
    private BigDecimal price;

    @NotNull(message = "Transmission type cannot be null")
    private TransmissionType transmission;

    @Positive(message = "Year must be a positive number")
    private Integer year;

    @NotNull(message = "Model cannot be null")
    private ModelDto model;

    @NotNull(message = "Seller cannot be null")
    private UserDto seller;

    public OfferDto(String description, EngineType engine, String imageUrl, Integer mileage, BigDecimal price, TransmissionType transmission, Integer year, ModelDto model, UserDto seller) {
        this.description = description;
        this.engine = engine;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.model = model;
        this.seller = seller;
    }

    public OfferDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EngineType getEngine() {
        return engine;
    }

    public void setEngine(EngineType engine) {
        this.engine = engine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TransmissionType getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public ModelDto getModel() {
        return model;
    }

    public void setModel(ModelDto model) {
        this.model = model;
    }

    public UserDto getSeller() {
        return seller;
    }

    public void setSeller(UserDto seller) {
        this.seller = seller;
    }
}
