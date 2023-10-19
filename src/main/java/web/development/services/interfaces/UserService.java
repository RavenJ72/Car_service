package web.development.services.interfaces;

import web.development.dto.input.UserDto;
import web.development.models.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService<ID> {

    UserDto save(UserDto user);  // Create/Update

    UserDto findById(ID id);  // Read

    List<UserDto> findAll();  // Read

    void deleteById(ID id);  // Delete
}