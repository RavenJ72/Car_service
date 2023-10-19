package web.development.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.development.models.entities.Model;


@Repository
public interface ModelRepository extends JpaRepository<Model, Long>{
}
