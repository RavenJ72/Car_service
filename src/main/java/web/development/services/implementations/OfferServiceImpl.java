package web.development.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import web.development.models.entities.User;
import web.development.models.enums.EngineType;
import web.development.models.enums.ModelCategory;
import web.development.models.enums.TransmissionType;
import web.development.repositories.ModelRepository;
import web.development.repositories.UserRepository;
import web.development.services.dto.input.OfferDto;
import web.development.services.dto.view.OfferOutputDto;
import web.development.models.entities.Offer;
import web.development.repositories.OfferRepository;
import web.development.services.exceptions.NotFoundException;
import web.development.services.exceptions.SaveException;
import web.development.services.interfaces.internalApi.OfferInternalService;
import web.development.services.interfaces.publicApi.OfferService;
import web.development.services.specifications.OfferSpecification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService<String>, OfferInternalService<String> {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ModelRepository modelRepository;


    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper, UserRepository userRepository, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public OfferDto save(OfferDto offerDto) {
            try {
                if (offerDto.getId() != null){
                    Offer offerEditObject = offerRepository.findById(offerDto.getId()).orElse(null);
                    offerEditObject.setModel(modelRepository.findById(offerDto.getModel()).orElse(null));
                    offerEditObject.setSeller(userRepository.findByUsername(offerDto.getSeller()));
                    offerEditObject.setDescription(offerDto.getDescription());
                    offerEditObject.setImageUrl(offerDto.getImageUrl());

                    return modelMapper.map(offerRepository.saveAndFlush(offerEditObject), OfferDto.class);

                }else{
                    Offer offer = modelMapper.map(offerDto, Offer.class);
                    offer.setSeller(userRepository.findByUsername(offerDto.getSeller()));
                    offer.setModel(modelRepository.findById(offerDto.getModel()).orElse(null));
                    return modelMapper.map(offerRepository.saveAndFlush(offer), OfferDto.class);
                }

            } catch (Exception e) {
                throw new SaveException("Failed to save the object.");
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
    public OfferOutputDto findOfferDetailsById(String id) {
        return modelMapper.map(offerRepository.findById(id), OfferOutputDto.class);
    }

    @Override
    public OfferDto findOfferForEdit(String id) {
        return modelMapper.map(offerRepository.findById(id), OfferDto.class);
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
