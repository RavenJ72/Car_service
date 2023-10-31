package web.development.services.interfaces.internalApi;

import web.development.models.entities.User;
import web.development.services.dto.input.UserDto;
import web.development.services.dto.output.UserOutputDto;

import java.util.List;

public interface UserInternalService<ID> {

    User findById(ID id);
    void deleteById(ID id);
}