package web.development.models.converters;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import web.development.models.enums.TransmissionType;

@Converter(autoApply = true)
public class TransmissionTypeConverter implements AttributeConverter<TransmissionType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TransmissionType attribute) {
        return attribute == null ? null : attribute.getTransmissionTypeCode();
    }

    @Override
    public TransmissionType convertToEntityAttribute(Integer dbData) {
        for (TransmissionType transmissionType : TransmissionType.values()) {
            if (transmissionType.getTransmissionTypeCode() == dbData) {
                return transmissionType;
            }
        }
        throw new IllegalArgumentException("Unknown TransmissionType code: " + dbData);
    }
}
