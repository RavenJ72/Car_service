package web.development.services.interfaces;

import web.development.models.entities.Brand;
import web.development.services.dto.input.BrandDto;

import java.util.List;


public interface BrandService<ID> {

    BrandDto save(String brand);  // Create/Update

    Brand findById(ID id);  // Read

    List<BrandDto> findAll();  // Read

    void deleteById(ID id);  // Delete

    BrandDto findByName(String name); //Read

}
