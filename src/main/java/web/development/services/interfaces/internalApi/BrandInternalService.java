package web.development.services.interfaces.internalApi;

import web.development.models.entities.Brand;
import web.development.services.dto.input.BrandDto;

import java.util.List;


public interface BrandInternalService<ID> {
    Brand findById(ID id);
    void deleteById(ID id);

}
