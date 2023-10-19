package web.development.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.development.models.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
