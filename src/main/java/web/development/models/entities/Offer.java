package web.development.models.entities;

import jakarta.persistence.*;
import web.development.models.baseEntities.TimeBaseEntity;
import web.development.models.converters.EngineTypeConverter;
import web.development.models.converters.TransmissionTypeConverter;
import web.development.models.enums.EngineType;
import web.development.models.enums.TransmissionType;


import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class Offer extends TimeBaseEntity {
    private String description;
    private EngineType engine;
    private String imageUrl;
    private Integer mileage;
    private BigDecimal price;
    private TransmissionType transmission;


    private Integer year;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "model_id")
    private Model model;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller_id")
    private User seller;

    public Offer() {
    }

    @Column(name = "description", columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Convert(converter = EngineTypeConverter.class)
    @Column(name = "engine", length = 11, nullable = false)
    public EngineType getEngine() {
        return engine;
    }

    private void setEngine(EngineType engine) {
        this.engine = engine;
    }

    @Column(name = "imageUrl", length = 512, nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "mileage", length = 11, nullable = false)
    public Integer getMileage() {
        return mileage;
    }

    private void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    @Column(name = "price", columnDefinition = "numeric(19,2)", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    private void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Convert(converter = TransmissionTypeConverter.class)
    @Column(name = "transmission", length = 11, nullable = false)
    public TransmissionType getTransmission() {
        return transmission;
    }

    private void setTransmission(TransmissionType transmission) {
        this.transmission = transmission;
    }

    @Column(name = "year", length = 11, nullable = false)
    public Integer getYear() {
        return year;
    }

    private void setYear(Integer year) {
        this.year = year;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
