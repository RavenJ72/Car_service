package web.development.services.implementations;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.services.dto.input.ModelDto;
import web.development.services.dto.view.ModelOutputDto;
import web.development.models.entities.Model;
import web.development.repositories.ModelRepository;
import web.development.services.exceptions.NotFoundException;
import web.development.services.exceptions.SaveException;
import web.development.services.exceptions.ValidationException;
import web.development.services.interfaces.internalApi.ModelInternalService;
import web.development.services.interfaces.publicApi.ModelService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService<String>, ModelInternalService<String> {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public ModelDto save(ModelDto model) {
            try {
                return modelMapper.map(modelRepository.saveAndFlush(modelMapper.map(model, Model.class)), ModelDto.class);
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
