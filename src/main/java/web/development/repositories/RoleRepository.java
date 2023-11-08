package web.development.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.development.models.entities.Role;
import web.development.models.enums.RoleType;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findAllByRole(RoleType role);
}
