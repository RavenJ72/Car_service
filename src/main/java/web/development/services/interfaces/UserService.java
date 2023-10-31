package web.development.services.interfaces;

import web.development.models.entities.User;
import web.development.services.dto.input.UserDto;
import web.development.services.dto.output.UserOutputDto;

import java.util.List;

public interface UserService<ID> {

    UserDto save(UserDto user);  // Create/Update

    User findById(ID id);  // Read

    List<UserOutputDto> findAll();  // Read

    void deleteById(ID id);  // Delete

    List<UserOutputDto> findUsersByActivity(Boolean isActive);
}