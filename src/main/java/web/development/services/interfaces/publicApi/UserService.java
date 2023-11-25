package web.development.services.interfaces.publicApi;

import web.development.services.dto.input.UserDto;
import web.development.services.dto.view.UserOutputDto;

import java.util.List;

public interface UserService<ID> {

    UserDto save(UserDto user);  // Create/Update

    List<UserOutputDto> findAll();  // Read

    UserDto findByUsername(String username);

    List<UserOutputDto> findUsersByActivity(Boolean isActive);
}