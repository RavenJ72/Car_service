package web.development.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import web.development.services.dto.input.BrandDto;
import web.development.models.entities.Brand;
import web.development.repositories.BrandRepository;
import web.development.services.exceptions.NotFoundException;
import web.development.services.exceptions.SaveException;
import web.development.services.exceptions.ValidationException;
import web.development.services.interfaces.internalApi.BrandInternalService;
import web.development.services.interfaces.publicApi.BrandService;
import jakarta.validation.ConstraintViolation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@EnableCaching
public class BrandServiceImpl implements BrandService<String>, BrandInternalService<String> {


    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @CacheEvict(cacheNames = "brands", allEntries = true)
    public BrandDto save(String brandName) {
        BrandDto brand = new BrandDto(brandName);
            try {
                return modelMapper.map(brandRepository.saveAndFlush(modelMapper.map(brand, Brand.class)), BrandDto.class);
            } catch (Exception e) {
                throw new SaveException("Failed to save the object.");

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
    @Cacheable("brands")
    public List<BrandDto> findAll() {
        return brandRepository.findAll().stream().map(e->modelMapper.map(e,BrandDto.class)).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(cacheNames = "brands", allEntries = true)
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
