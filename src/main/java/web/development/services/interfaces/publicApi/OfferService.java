package web.development.services.interfaces.publicApi;

import web.development.services.dto.input.OfferDto;
import web.development.services.dto.view.OfferOutputDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OfferService<ID> {

    OfferDto save(OfferDto offer);  // Create/Update

    List<OfferOutputDto> findAll();  // Read

    OfferOutputDto findOfferDetailsById(String id);

    List<OfferOutputDto> findOffersByBrandName(String brandName);

    List<OfferOutputDto> findOffersBySellerUsername(String username);

    List<OfferOutputDto> findOffersByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);

    List<OfferOutputDto> findFilteredOffers(String brandName,String engineType,String modelType,String transmission);


}