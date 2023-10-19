package web.development.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import web.development.dto.input.BrandDto;
import web.development.models.entities.Brand;
import web.development.repositories.BrandRepository;
import web.development.services.interfaces.BrandService;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BrandServiceImpl implements BrandService<Long> {


    private final BrandRepository brandRepository;

    private final ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public BrandDto save(BrandDto brand) {
        return modelMapper.map(brandRepository.save(modelMapper.map(brand,Brand.class)),BrandDto.class);
    }

    @Override
    public BrandDto findById(Long id) {
        return modelMapper.map(brandRepository.findById(id).orElse(null),BrandDto.class);
    }

    @Override
    public List<BrandDto> findAll() {
        return brandRepository.findAll().stream().map(e->modelMapper.map(e,BrandDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }
}
