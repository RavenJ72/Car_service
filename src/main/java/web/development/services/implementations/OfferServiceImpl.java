package web.development.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.dto.input.OfferDto;
import web.development.models.entities.Offer;
import web.development.repositories.OfferRepository;
import web.development.services.interfaces.OfferService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService<Long> {

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
    public OfferDto findById(Long id) {
        return modelMapper.map(offerRepository.findById(id).orElse(null), OfferDto.class);
    }

    @Override
    public List<OfferDto> findAll() {
        return offerRepository.findAll().stream().map(e -> modelMapper.map(e, OfferDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        offerRepository.deleteById(id);
    }
}
