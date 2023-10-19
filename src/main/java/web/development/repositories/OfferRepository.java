package web.development.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.development.models.entities.Offer;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {


    List<Offer> findByModel_Brand_Name(String brandName);

    List<Offer> findBySeller_Username(String username);

    List<Offer> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);











}
