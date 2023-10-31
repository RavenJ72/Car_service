package web.development.services.interfaces.internalApi;

import web.development.models.entities.Offer;
import web.development.services.dto.input.OfferDto;
import web.development.services.dto.output.OfferOutputDto;

import java.math.BigDecimal;
import java.util.List;

public interface OfferInternalService<ID> {

    Offer findById(ID id);
    void deleteById(ID id);



}