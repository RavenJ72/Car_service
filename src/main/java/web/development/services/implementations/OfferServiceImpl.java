package web.development.services.implementations;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import web.development.models.entities.Model;
import web.development.models.enums.EngineType;
import web.development.models.enums.ModelCategory;
import web.development.models.enums.TransmissionType;
import web.development.services.dto.input.ModelDto;
import web.development.services.dto.input.OfferDto;
import web.development.services.dto.output.OfferOutputDto;
import web.development.models.entities.Offer;
import web.development.repositories.OfferRepository;
import web.development.services.exceptions.NotFoundException;
import web.development.services.exceptions.SaveException;
import web.development.services.exceptions.ValidationException;
import web.development.services.interfaces.internalApi.OfferInternalService;
import web.development.services.interfaces.publicApi.OfferService;
import web.development.services.specifications.OfferSpecification;
import web.development.util.ValidationUtilImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            String exceptionMessage = "The data is not valid:\n";
            List<String> validationErrors = new ArrayList<>(this.validationUtil
                    .violations(offer)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList()));

            exceptionMessage += String.join("\n", validationErrors);
            throw new ValidationException(exceptionMessage);

        } else {
            try {
                return modelMapper.map(offerRepository.saveAndFlush(modelMapper.map(offer, Offer.class)), OfferDto.class);
            } catch (Exception e) {
                throw new SaveException("Failed to save the object.");
            }
        }
    }

    @Override
    public Offer findById(String id) {
        Optional<Offer> offer = offerRepository.findById(id);
        if (offer.isEmpty()){
            throw new NotFoundException("Offer with this id not found");
        }
        return (Offer) offer.get();
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

    @Override
    public List<OfferOutputDto> findFilteredOffers(String brandName, String engineType, String modelCategory, String transmission) {


        Specification<Offer> spec = Specification.where(OfferSpecification.hasBrandName(brandName))
                .and(OfferSpecification.hasEngineType( EngineType.getEngineTypeCodeFromString(engineType)))
                .and(OfferSpecification.hasTransmission(TransmissionType.getTransmissionTypeCodeFromString(transmission)))
                .and(OfferSpecification.hasModelCategory(ModelCategory.getModelCategoryCodeFromString(modelCategory)));


        List<Offer> offers = offerRepository.findAll(spec);

        return offers.stream()
                .map(offer -> modelMapper.map(offer, OfferOutputDto.class))
                .collect(Collectors.toList());
    }

}
