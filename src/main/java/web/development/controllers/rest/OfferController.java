package web.development.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.development.services.dto.input.ModelDto;
import web.development.services.dto.input.OfferDto;
import web.development.services.dto.output.ModelOutputDto;
import web.development.services.dto.output.OfferOutputDto;
import web.development.services.interfaces.publicApi.OfferService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/offer")
public class OfferController {

    private final OfferService offerService;
    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/findAll")
    public List<OfferOutputDto> findAll() {
        return offerService.findAll();
    }
    @PostMapping("/create")
    public OfferDto save(OfferDto offer){
        return offerService.save(offer);
    }

    @GetMapping("/findByBrandName")
    public List<OfferOutputDto> findByBrandName(@RequestParam String brandName){
        return offerService.findOffersByBrandName(brandName);
    }

    @GetMapping("/findBySellerUsername")
    public List<OfferOutputDto> findBySellerUsername(@RequestParam String username){
        return offerService.findOffersBySellerUsername(username);
    }

    @GetMapping("/findOffersByPriceBetween")
    public List<OfferOutputDto> findOffersByPriceBetween(@RequestParam BigDecimal firstPrice,@RequestParam BigDecimal secondPrice){
        return offerService.findOffersByPriceBetween(firstPrice,secondPrice);
    }

}
