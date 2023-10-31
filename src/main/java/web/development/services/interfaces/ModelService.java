package web.development.services.interfaces;

import web.development.models.entities.Model;
import web.development.services.dto.input.ModelDto;
import web.development.services.dto.output.ModelOutputDto;

import java.util.List;


public interface ModelService<ID> {

    ModelDto save(ModelDto model);  // Create/Update

    Model findById(ID id);  // Read

    List<ModelOutputDto> findAll();  // Read

    void deleteById(ID id);  // Delete

    List<ModelOutputDto> findByBrandName(String name);
}