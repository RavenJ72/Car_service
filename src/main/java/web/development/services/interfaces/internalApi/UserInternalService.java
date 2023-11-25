package web.development.services.interfaces.internalApi;

import web.development.models.entities.User;

public interface UserInternalService<ID> {

    User findById(ID id);
    void deleteById(ID id);
}