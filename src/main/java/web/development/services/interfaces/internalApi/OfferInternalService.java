package web.development.services.interfaces.internalApi;

import web.development.models.entities.Offer;

public interface OfferInternalService<ID> {

    Offer findById(ID id);
    void deleteById(ID id);



}