package web.development.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.services.dto.input.ModelDto;
import web.development.services.dto.output.ModelOutputDto;
import web.development.models.entities.Model;
import web.development.repositories.ModelRepository;
import web.development.services.interfaces.ModelService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService<String> {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ModelDto save(ModelDto model) {
        return modelMapper.map(modelRepository.save(modelMapper.map(model, Model.class)), ModelDto.class);
    }

    @Override
    public ModelDto findById(String id) {
        return modelMapper.map(modelRepository.findById(id).orElse(null), ModelDto.class);
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
