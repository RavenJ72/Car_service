package web.development.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.repositories.BrandRepository;
import web.development.repositories.OfferRepository;
import web.development.services.dto.input.ModelDto;
import web.development.services.dto.view.ModelOutputDto;
import web.development.models.entities.Model;
import web.development.repositories.ModelRepository;
import web.development.services.exceptions.NotFoundException;
import web.development.services.exceptions.SaveException;
import web.development.services.interfaces.internalApi.ModelInternalService;
import web.development.services.interfaces.publicApi.ModelService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService<String>, ModelInternalService<String> {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    private final BrandRepository brandRepository;


    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper, OfferRepository offerRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;


        this.brandRepository = brandRepository;
    }

    @Override
    public ModelDto save(ModelDto model) {
            try {
                Model modelForSave = modelMapper.map(model,Model.class);
                modelForSave.setBrand(brandRepository.findByName(model.getBrand()));
                return modelMapper.map(modelRepository.saveAndFlush(modelForSave), ModelDto.class);
            } catch (Exception e) {
                throw new SaveException("Failed to save the model.");
            }
    }

    @Override
    public Model findById(String id) {
        Optional<Model> model = modelRepository.findById(id);
        if (model.isEmpty()){
            throw new NotFoundException("Model with this id not found");
        }
        return (Model) model.get();
    }

    @Override
    public List<ModelOutputDto> findAll() {
        return modelRepository.findAll().stream().map(e -> modelMapper.map(e, ModelOutputDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        modelRepository.deleteById(id);
    }

    @Override
    public List<ModelOutputDto> findByBrandName(String name) {
        return modelRepository.findByBrand_Name(name).stream().map(e -> modelMapper.map(e, ModelOutputDto.class)).collect(Collectors.toList());
    }
}
