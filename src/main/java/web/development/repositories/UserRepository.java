package web.development.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.development.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
