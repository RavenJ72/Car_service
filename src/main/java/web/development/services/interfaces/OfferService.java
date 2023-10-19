package web.development.services.interfaces;

import web.development.dto.input.OfferDto;
import web.development.dto.output.OfferOutputDto;
import web.development.models.entities.Offer;

import java.math.BigDecimal;
import java.util.List;

public interface OfferService<ID> {

    OfferDto save(OfferDto offer);  // Create/Update

    OfferDto findById(ID id);  // Read

    List<OfferOutputDto> findAll();  // Read

    void deleteById(ID id);  // Delete

    List<OfferOutputDto> findOffersByBrandName(String brandName);

    List<OfferOutputDto> findOffersBySellerUsername(String username);

    List<OfferOutputDto> findOffersByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);


}