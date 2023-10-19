package web.development.services.interfaces;

import web.development.dto.input.ModelDto;
import web.development.dto.output.ModelOutputDto;
import web.development.models.entities.Model;

import java.util.List;


public interface ModelService<ID> {

    ModelDto save(ModelDto model);  // Create/Update

    ModelDto findById(ID id);  // Read

    List<ModelOutputDto> findAll();  // Read

    void deleteById(ID id);  // Delete

    List<ModelOutputDto> findByBrandName(String name);
}