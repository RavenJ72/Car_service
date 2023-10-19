package web.development.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.dto.input.ModelDto;
import web.development.models.entities.Model;
import web.development.repositories.ModelRepository;
import web.development.services.interfaces.ModelService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService<Long> {

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
    public ModelDto findById(Long id) {
        return modelMapper.map(modelRepository.findById(id).orElse(null), ModelDto.class);
    }

    @Override
    public List<ModelDto> findAll() {
        return modelRepository.findAll().stream().map(e -> modelMapper.map(e, ModelDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        modelRepository.deleteById(id);
    }
}
