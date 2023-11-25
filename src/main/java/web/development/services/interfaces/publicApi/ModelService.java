package web.development.services.interfaces.publicApi;

import web.development.services.dto.input.ModelDto;
import web.development.services.dto.view.ModelOutputDto;

import java.util.List;


public interface ModelService<ID> {

    ModelDto save(ModelDto model);  // Create/Update

    List<ModelOutputDto> findAll();  // Read

    List<ModelOutputDto> findByBrandName(String name);
}