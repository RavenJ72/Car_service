package web.development.services.specifications;

import org.springframework.data.jpa.domain.Specification;

import web.development.models.entities.Offer;
import web.development.models.enums.EngineType;
import web.development.models.enums.ModelCategory;
import web.development.models.enums.TransmissionType;


public class OfferSpecification {
    public static Specification<Offer> hasBrandName(String brandName) {
        return (root, query, criteriaBuilder) -> {
            if (brandName == null || brandName.isEmpty()) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.join("model").join("brand").get("name"), brandName);
        };
    }

    public static Specification<Offer> hasEngineType(Integer engineType) {
        return (root, query, criteriaBuilder) -> {
            if (engineType == null) {
                return criteriaBuilder.conjunction();
            }

            return criteriaBuilder.equal(root.get("engine"), EngineType.fromEngineTypeCode(engineType));
        };
    }


    public static Specification<Offer> hasTransmission(Integer transmission) {
        return (root, query, criteriaBuilder) -> {
            if (transmission == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("transmission"), TransmissionType.fromTransmissionTypeCode(transmission));
        };
    }

    public static Specification<Offer> hasModelCategory(Integer modelCategory) {
        return (root, query, criteriaBuilder) -> {
            if (modelCategory == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.join("model").get("category"), ModelCategory.fromModelCategoryCode(modelCategory));
        };
    }




}
