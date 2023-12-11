package web.development.services.interfaces.publicApi;

import web.development.models.entities.User;
import web.development.services.dto.input.OfferDto;
import web.development.services.dto.input.UserDto;
import web.development.services.dto.view.OfferOutputDto;
import web.development.services.dto.view.UserOutputDto;

import java.util.List;

public interface UserService<ID> {

    UserDto save(UserDto user);  // Create/Update

    List<UserOutputDto> findAll();  // Read

    UserDto findByUsername(String username);

    UserDto findUserForEdit(String id);

    UserOutputDto findUserDetailsById(String id);
    UserOutputDto findUserDetailsByUsername(String username);

    List<UserOutputDto> findUsersByActivity(Boolean isActive);
}