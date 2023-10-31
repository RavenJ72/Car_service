package web.development.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.services.dto.input.OfferDto;
import web.development.services.dto.output.OfferOutputDto;
import web.development.models.entities.Offer;
import web.development.repositories.OfferRepository;
import web.development.services.interfaces.OfferService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService<String> {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public OfferDto save(OfferDto offer) {
        return modelMapper.map(offerRepository.save(modelMapper.map(offer, Offer.class)), OfferDto.class);
    }

    @Override
    public OfferDto findById(String id) {
        return modelMapper.map(offerRepository.findById(id).orElse(null), OfferDto.class);
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
