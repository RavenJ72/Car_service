package web.development.services.interfaces;

import web.development.dto.input.BrandDto;
import web.development.models.entities.Brand;

import java.util.List;


public interface BrandService<ID> {

    BrandDto save(BrandDto brand);  // Create/Update

    BrandDto findById(ID id);  // Read

    List<BrandDto> findAll();  // Read

    void deleteById(ID id);  // Delete

    BrandDto findByName(String name); //Read

}
