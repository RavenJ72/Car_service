package web.development.services.interfaces.publicApi;

import web.development.models.entities.Brand;
import web.development.services.dto.input.BrandDto;

import java.util.List;


public interface BrandService<ID> {

    BrandDto save(String brand);  // Create/Update

    List<BrandDto> findAll();  // Read


    BrandDto findByName(String name); //Read

}
