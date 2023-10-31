package web.development.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import web.development.services.dto.input.BrandDto;
import web.development.models.entities.Brand;
import web.development.repositories.BrandRepository;
import web.development.services.interfaces.BrandService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BrandServiceImpl implements BrandService<String> {


    private final BrandRepository brandRepository;

    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public BrandDto save(String brandName) {
        BrandDto brand = new BrandDto(brandName);
        return modelMapper.map(brandRepository.saveAndFlush(modelMapper.map(brand,Brand.class)),BrandDto.class);
    }

    @Override
    public Brand findById(String id) {
        return brandRepository.findById(id).orElse(null);
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
        return modelMapper.map(brandRepository.findAllByName(name).stream().findFirst().orElse(null),BrandDto.class);
    }
}
