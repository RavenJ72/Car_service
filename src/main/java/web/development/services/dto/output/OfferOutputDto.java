package web.development.services.dto.output;

import web.development.models.enums.EngineType;
import web.development.models.enums.TransmissionType;

import java.math.BigDecimal;


public class OfferOutputDto {


    public String description;

    public EngineType engine;

    public String imageUrl;

    public Integer mileage;

    public BigDecimal price;

    public TransmissionType transmission;

    public Integer year;

    public String model_id;
    public String seller_id;

    public OfferOutputDto(String description, EngineType engine, String imageUrl, Integer mileage, BigDecimal price, TransmissionType transmission, Integer year, String model_id, String seller_id) {
        this.description = description;
        this.engine = engine;
        this.imageUrl = imageUrl;
        this.mileage = mileage;
        this.price = price;
        this.transmission = transmission;
        this.year = year;
        this.model_id = model_id;
        this.seller_id = seller_id;
    }

    public OfferOutputDto() {
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

    public String getModel_id() {
        return model_id;
    }

    public void setModel_id(String model_id) {
        this.model_id = model_id;
    }

    @Override
    public String toString() {
        return "OfferOutputDto{" +
                "description='" + description + '\'' +
                ", engine=" + engine +
                ", imageUrl='" + imageUrl + '\'' +
                ", mileage=" + mileage +
                ", price=" + price +
                ", transmission=" + transmission +
                ", year=" + year +
                ", model_id=" + model_id +
                ", seller_id=" + seller_id +
                '}';
    }

    public String getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(String seller_id) {
        this.seller_id = seller_id;
    }
}
