package web.development.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.development.models.entities.Brand;

import java.util.List;


@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {

    List<Brand> findAllByName(String name);

    Brand findByName(String name);
}
