package web.development.models.enums;

public enum TransmissionType {
    MANUAL(1),
    AUTOMATIC(2);

    private final int transmissionTypeCode;

    TransmissionType(int transmissionTypeCode) {
        this.transmissionTypeCode = transmissionTypeCode;
    }

    public int getTransmissionTypeCode() {
        return transmissionTypeCode;
    }

    public static Integer getTransmissionTypeCodeFromString(String transmissionTypeName) {
        for (TransmissionType transmissionType : TransmissionType.values()) {
            if (transmissionType.name().equalsIgnoreCase(transmissionTypeName)) {
                return transmissionType.getTransmissionTypeCode();
            }
        }
        return null;
    }

    public static TransmissionType fromTransmissionTypeCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (TransmissionType transmissionType : TransmissionType.values()) {
            if (transmissionType.getTransmissionTypeCode() == code) {
                return transmissionType;
            }
        }
        throw new IllegalArgumentException("No TransmissionType with code " + code + " found");
    }
}
