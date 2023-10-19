package web.development.models.converters;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import web.development.models.enums.RoleType;


@Converter(autoApply = true)
public class RoleTypeConverter implements AttributeConverter<RoleType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RoleType attribute) {
        System.out.println(attribute.getUserRoleTypeCode());
        return attribute == null ? null : attribute.getUserRoleTypeCode();
    }

    @Override
    public RoleType convertToEntityAttribute(Integer dbData) {
        for (RoleType roleType : RoleType.values()) {
            if (roleType.getUserRoleTypeCode() == dbData) {
                return roleType;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
