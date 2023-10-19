package web.development.services.interfaces;

import web.development.dto.input.OfferDto;
import web.development.models.entities.Offer;

import java.util.List;

public interface OfferService<ID> {

    OfferDto save(OfferDto offer);  // Create/Update

    OfferDto findById(ID id);  // Read

    List<OfferDto> findAll();  // Read

    void deleteById(ID id);  // Delete
}