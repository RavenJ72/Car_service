package web.development.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.development.models.entities.User;
import web.development.services.dto.input.UserDto;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByIsActive(Boolean isActive);

    User findByUsername(String username);


}
