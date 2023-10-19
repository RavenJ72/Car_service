package web.development.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.development.models.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
