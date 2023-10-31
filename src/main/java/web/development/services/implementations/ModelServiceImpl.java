package web.development.services.implementations;

import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.services.dto.input.ModelDto;
import web.development.services.dto.output.ModelOutputDto;
import web.development.models.entities.Model;
import web.development.repositories.ModelRepository;
import web.development.services.interfaces.internalApi.ModelInternalService;
import web.development.services.interfaces.publicApi.ModelService;
import web.development.util.ValidationUtilImpl;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModelServiceImpl implements ModelService<String>, ModelInternalService<String> {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public ModelDto save(ModelDto model) {

        if (!this.validationUtil.isValid(model)) {
            this.validationUtil
                    .violations(model)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);

        } else {
            try {
                return modelMapper.map(modelRepository.saveAndFlush(modelMapper.map(model, Model.class)), ModelDto.class);
            } catch (Exception e) {
                System.out.println("Some thing went wrong!");
            }
        }

        return null;

    }

    @Override
    public Model findById(String id) {
        return modelRepository.findById(id).orElse(null);
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
