package web.development.services.interfaces.internalApi;

import web.development.models.entities.Model;


public interface ModelInternalService<ID> {

    Model findById(ID id);  // Read

    void deleteById(ID id);  // Delete

}