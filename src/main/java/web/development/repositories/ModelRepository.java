package web.development.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.development.models.entities.Model;

import java.util.List;
import java.util.UUID;


@Repository
public interface ModelRepository extends JpaRepository<Model, String>{

    List<Model> findByBrand_Name(String name);

}
