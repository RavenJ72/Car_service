package web.development.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.development.services.dto.input.BrandDto;
import web.development.models.entities.Brand;
import web.development.repositories.BrandRepository;
import web.development.services.exceptions.NotFoundException;
import web.development.services.exceptions.SaveException;
import web.development.services.exceptions.ValidationException;
import web.development.services.interfaces.internalApi.BrandInternalService;
import web.development.services.interfaces.publicApi.BrandService;
import web.development.util.ValidationUtilImpl;
import jakarta.validation.ConstraintViolation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BrandServiceImpl implements BrandService<String>, BrandInternalService<String> {


    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;
    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public BrandDto save(String brandName) {
        BrandDto brand = new BrandDto(brandName);

        if (!this.validationUtil.isValid(brand)) {
            String exceptionMessage = "The data is not valid:\n";
            List<String> validationErrors = new ArrayList<>(this.validationUtil
                    .violations(brand)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toList()));

            exceptionMessage += String.join("\n", validationErrors);
            throw new ValidationException(exceptionMessage);

        } else {
            try {
                return modelMapper.map(brandRepository.saveAndFlush(modelMapper.map(brand, Brand.class)), BrandDto.class);
            } catch (Exception e) {
                throw new SaveException("Failed to save the object.");

            }
        }
    }

    @Override
    public Brand findById(String id) {
        Optional<Brand> brand = brandRepository.findById(id);
       if (brand.isEmpty()){
           throw new NotFoundException("Brand with this id not found");
       }
        return (Brand) brand.get();
    }

    @Override
    public List<BrandDto> findAll() {
        return brandRepository.findAll().stream().map(e->modelMapper.map(e,BrandDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        brandRepository.deleteById(id);
    }

    @Override
    public BrandDto findByName(String name) {
        Optional brandDto = brandRepository.findAllByName(name).stream().findFirst();
        if(!brandDto.isPresent()){
            throw new NotFoundException("Brand with this name not found");
        }
        return modelMapper.map(brandDto.get(),BrandDto.class);
    }
}
