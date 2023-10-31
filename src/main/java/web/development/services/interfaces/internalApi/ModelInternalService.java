package web.development.services.interfaces.internalApi;

import web.development.models.entities.Model;
import web.development.services.dto.input.ModelDto;
import web.development.services.dto.output.ModelOutputDto;

import java.util.List;


public interface ModelInternalService<ID> {

    Model findById(ID id);  // Read

    void deleteById(ID id);  // Delete

}