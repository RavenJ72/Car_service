package web.development.services.implementations;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.services.dto.input.OfferDto;
import web.development.services.dto.output.OfferOutputDto;
import web.development.models.entities.Offer;
import web.development.repositories.OfferRepository;
import web.development.services.interfaces.internalApi.OfferInternalService;
import web.development.services.interfaces.publicApi.OfferService;
import web.development.util.ValidationUtilImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService<String>, OfferInternalService<String> {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public OfferDto save(OfferDto offer) {
        if (!this.validationUtil.isValid(offer)) {
            this.validationUtil
                    .violations(offer)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else {
            try {
                return modelMapper.map(offerRepository.saveAndFlush(modelMapper.map(offer, Offer.class)), OfferDto.class);
            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
            }
        }

        return null;

    }

    @Override
    public Offer findById(String id) {
        return offerRepository.findById(id).orElse(null);
    }

    @Override
    public List<OfferOutputDto> findAll() {
        return offerRepository.findAll().stream().map(e -> modelMapper.map(e, OfferOutputDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        offerRepository.deleteById(id);
    }

    @Override
    public List<OfferOutputDto> findOffersByBrandName(String brandName) {
        return offerRepository.findByModel_Brand_Name(brandName).stream().map(e -> modelMapper.map(e, OfferOutputDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OfferOutputDto> findOffersBySellerUsername(String username) {
        return offerRepository.findBySeller_Username(username).stream().map(e -> modelMapper.map(e, OfferOutputDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<OfferOutputDto> findOffersByPriceBetween(BigDecimal startPrice, BigDecimal endPrice) {
        return offerRepository.findByPriceBetween(startPrice,endPrice).stream().map(e -> modelMapper.map(e, OfferOutputDto.class)).collect(Collectors.toList());
    }


}
