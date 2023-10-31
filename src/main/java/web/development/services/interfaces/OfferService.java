package web.development.services.interfaces;

import web.development.models.entities.Offer;
import web.development.services.dto.input.OfferDto;
import web.development.services.dto.output.OfferOutputDto;

import java.math.BigDecimal;
import java.util.List;

public interface OfferService<ID> {

    OfferDto save(OfferDto offer);  // Create/Update

    Offer findById(ID id);  // Read

    List<OfferOutputDto> findAll();  // Read

    void deleteById(ID id);  // Delete

    List<OfferOutputDto> findOffersByBrandName(String brandName);

    List<OfferOutputDto> findOffersBySellerUsername(String username);

    List<OfferOutputDto> findOffersByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);


}